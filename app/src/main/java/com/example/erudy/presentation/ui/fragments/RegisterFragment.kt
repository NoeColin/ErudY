package com.example.erudy.presentation.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.erudy.R
import com.example.erudy.base.BaseFragment
import com.example.erudy.presentation.presenter.presenter.RegisterFragmentPresenter
import com.example.erudy.presentation.presenter.view.RegisterView
import com.example.erudy.presentation.ui.activity.ContainerActivity
import com.example.erudy.presentation.ui.activity.LoginActivity
import com.yanzhenjie.album.Album
import com.yanzhenjie.durban.Controller
import com.yanzhenjie.durban.Durban
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_login.loader
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject


class RegisterFragment : BaseFragment<RegisterFragmentPresenter>(), RegisterView {

    @Inject
    override lateinit var presenter: RegisterFragmentPresenter
    override val layoutId: Int = R.layout.fragment_register

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun displayLoader() {
        loader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        loader.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login.setOnClickListener {
            (activity as LoginActivity).goToLogin()
        }
        register_button.setOnClickListener {
            signup()
        }
        profile_image.setOnClickListener {
            Album.image(this) // Image and video mix options.
                .singleChoice() // Multi-Mode, Single-Mode: singleChoice().
                .columnCount(3) // The number of columns in the page list.
                .camera(true) // Whether the camera appears in the Item.
//                .filterMimeType() // Filter file format.
                .onResult {
                    Durban.with(this)
                        // Che title of the UI.
                        .title("Crop")
                        // Image path list/array.
                        .inputImagePaths(it[0].path)
                        // Image output directory.
                        .outputDirectory(context!!.applicationInfo.dataDir)
                        // Image size limit.
                        .maxWidthHeight(500, 500)
                        // Aspect ratio.
                        .aspectRatio(1f, 1f)
                        // Output format: JPEG, PNG.
                        .compressFormat(Durban.COMPRESS_JPEG)
                        // Compress quality, see Bitmap#compress(Bitmap.CompressFormat, int, OutputStream)
                        .compressQuality(90)
                        // Gesture: ROTATE, SCALE, ALL, NONE.
                        .gesture(Durban.GESTURE_ALL)
                        .controller(
                            Controller.newBuilder() // Create Builder of Controller.
                                .enable(false) // Enable the control panel.
                                .rotation(true) // Rotation button.
                                .rotationTitle(true) // Rotation button title.
                                .scale(true) // Scale button.
                                .scaleTitle(true) // Scale button title.
                                .build()) // Create Controller Config.
                        .requestCode(200)
                        .start()
                }
                .onCancel {

                }
                .start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            200 -> {
                // Analyze the list of paths after cropping.
                if (resultCode == RESULT_OK) {
                    val mImageList = Durban.parseResult(data!!)
                    profile_image.setImageURI(mImageList[0].toUri())
                }
            }
        }
    }

    fun signup() {
        val bitmap = (profile_image.drawable.current as BitmapDrawable).bitmap
        presenter.signup(edit_first_name.text.toString(), edit_last_name.text.toString(), edit_email.text.toString(), edit_password.text.toString(), edit_confirm_password.text.toString(), bitmap)
    }

    override fun goToMain() {
        val intent = Intent(context, ContainerActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}