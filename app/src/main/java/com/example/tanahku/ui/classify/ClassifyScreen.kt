package com.example.tanahku.ui.classify

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.tanahku.R
import com.example.tanahku.ViewModelFactory
import com.example.tanahku.di.Injection
import com.example.tanahku.ml.LiteModel
import com.example.tanahku.ui.auth.login.LoginViewModel
import com.example.tanahku.ui.component.TanahKuButton
import com.example.tanahku.ui.navigation.Screen
import com.example.tanahku.ui.theme.TanahKuTheme
import com.example.tanahku.utils.Utils.getImageUri
import com.example.tanahku.utils.Utils.preprocessImage


val LocalImageUri = compositionLocalOf<Uri?> { null }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassifyScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ClassifyViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
) {
    var currentImageUri by remember { mutableStateOf<Uri?>(Uri.EMPTY) }
    val context = LocalContext.current
    val uri = getImageUri(context)
    var result by remember { mutableStateOf("") }


    val launcherCameraIntent =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            currentImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            launcherCameraIntent.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val launcherGallery = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    fun classifySoil() {
        if (currentImageUri == Uri.EMPTY) {
            Toast.makeText(context, "Select an image first", Toast.LENGTH_SHORT).show()
            return
        }
        val bitmap = try {
            BitmapFactory.decodeStream(currentImageUri?.let {
                context.contentResolver.openInputStream(
                    it
                )
            })
        } catch (e: Exception) {
            Toast.makeText(context, "Error loading image", Toast.LENGTH_SHORT).show()
            return
        }
        val inputFeature0 = preprocessImage(bitmap)
        val model = LiteModel.newInstance(context)
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        val confidences = outputFeature0.floatArray

        var maxPos = 0
        var maxConfidence = 0f
        for (i in confidences.indices) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i]
                maxPos = i
            }
        }
        val classes = arrayOf("Chalk", "Clay", "Loam", "Peat", "Sand", "Silt")

        result = classes[maxPos]

        model.close()

    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color(0xffefd3ae))
            .padding(all = 16.dp)
    ) {
        currentImageUri?.let { viewModel.setCurrentImageUri(it) }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.title_activity_classify_soil),
                color = Color(0xff2e2836),
                style = TextStyle(
                    fontSize = 30.sp, fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
            )
            Text(
                text = stringResource(id = R.string.desc_activity_classify_soil),
                color = Color(0xff2e2836),
                style = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
            )
        }

        currentImageUri?.let { viewModel.setCurrentImageUri(it) }

        ShowImage(uri = currentImageUri)

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ) {
                TanahKuButton(title = "Camera", onClick = {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        launcherCameraIntent.launch(uri)
                    } else {
                        // Request a permission
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }, width = 170, height = 42)
                TanahKuButton(title = "Gallery", onClick = {
                    launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }, width = 170, height = 42)
            }
            TanahKuButton(
                title = "Klasifikasikan Tanah Anda",
                onClick = {
                    classifySoil()
                    val uri = viewModel.currentImageUri.value
                    navController.navigate(
                        "${Screen.ClassificationResult.route}?${Screen.ClassificationResult.ARG_IMAGE_URI}=${
                            Uri.encode(
                                currentImageUri.toString()
                            )
                        }&${Screen.ClassificationResult.ARG_ML_RESULT}=${result}"
                    )
                },
                width = 353,
                height = 39,
                color = Color(0xff2F1908)
            )
        }
    }
}

@Composable
fun ShowImage(uri: Uri?) {
    Box(
        modifier = Modifier
            .requiredSize(380.dp, 430.dp)
            .wrapContentSize(Alignment.Center)
            .padding(16.dp)
    ) {
        Image(
            painter = if (uri?.path?.isNotEmpty() == true) {
                rememberImagePainter(uri)
            } else {
                painterResource(id = R.drawable.baseline_image_24)
            },
            contentDescription = "Foto Tanah",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .border(
                    border = BorderStroke(2.dp, Color(0xff1f1104)),
                    shape = RoundedCornerShape(10.dp)
                )
        )

    }
}


