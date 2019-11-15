package com.example.erudy.presentation.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide

import com.example.erudy.R
import com.example.erudy.base.BaseFragment
import com.example.erudy.presentation.presenter.presenter.EditProfileFragmentPresenter
import com.example.erudy.presentation.presenter.view.EditProfileView
import com.example.erudy.presentation.ui.activity.EditProfileActivity
import com.yanzhenjie.album.Album
import com.yanzhenjie.durban.Controller
import com.yanzhenjie.durban.Durban
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.edit_email
import kotlinx.android.synthetic.main.fragment_edit_profile.edit_first_name
import kotlinx.android.synthetic.main.fragment_edit_profile.edit_last_name
import kotlinx.android.synthetic.main.fragment_edit_profile.loader
import kotlinx.android.synthetic.main.fragment_edit_profile.profile_image
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditProfileFragment : BaseFragment<EditProfileFragmentPresenter>(), EditProfileView {
    @Inject
    override lateinit var presenter: EditProfileFragmentPresenter

    private var param1: String? = null

    private var param2: String? = null
    override val layoutId: Int = R.layout.fragment_edit_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun goToProfile() {
        (activity as EditProfileActivity).finish()
    }

    // private var listener: OnFragmentInteractionListener? = null
    override fun displayLoader() {
        loader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        loader.visibility = View.GONE
    }

    override fun displayProfile(lastname: String, firstName: String, email: String, image: String) {
        edit_last_name.setText(lastname)
        edit_first_name.setText(firstName)
        edit_email.setText(email)
        Glide.with(context!!).load(image).into(profile_image)
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
       // listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadProfile()

        save_button.setOnClickListener {
            saveUpdate()
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
                        .statusBarColor(ContextCompat.getColor(context!!, R.color.colorErudYPrimaryDark))
                        .toolBarColor(ContextCompat.getColor(context!!, R.color.colorErudYPrimary))
                        .navigationBarColor(ContextCompat.getColor(context!!, R.color.colorErudYPrimary))
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
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            200 -> {
                // Analyze the list of paths after cropping.
                if (resultCode == Activity.RESULT_OK) {
                    val mImageList = Durban.parseResult(data!!)
                    profile_image.setImageURI(mImageList[0].toUri())
                } else {
                    Toast.makeText(context, "Error: Try again later", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun saveUpdate() {
        val bitmap = (profile_image.drawable.current as BitmapDrawable).bitmap
        presenter.updateProfile(edit_first_name.text.toString(), edit_last_name.text.toString(), edit_email.text.toString(), bitmap)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment editProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
