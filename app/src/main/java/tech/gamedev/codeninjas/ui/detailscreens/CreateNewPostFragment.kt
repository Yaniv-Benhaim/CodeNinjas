package tech.gamedev.codeninjas.ui.detailscreens

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.data.models.posts.Post
import tech.gamedev.codeninjas.databinding.FragmentCreateNewPostBinding
import tech.gamedev.codeninjas.ui.discuss.DiscussViewModel
import java.io.File


class CreateNewPostFragment : Fragment(R.layout.fragment_create_new_post) {

    private lateinit var binding: FragmentCreateNewPostBinding
    private val discussViewModel by activityViewModels<DiscussViewModel>()
    private lateinit var file: File

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateNewPostBinding.bind(view)

        binding.btnChooseImage.setOnClickListener { chooseImage() }
        binding.btnCreatePost.setOnClickListener { preparePostForUpload() }
    }

    private fun chooseImage() {
        ImagePicker.with(this)
            .crop(1f, 1f)
            .compress(1024)
            .maxResultSize(1080, 1080)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> {

                val fileUri = data?.data
                binding.ivPostImage.setImageURI(fileUri)
                file = ImagePicker.getFile(data)!!
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun preparePostForUpload() {
        val title = binding.etTitle.editText?.text.toString()
        val body = binding.etBody.editText?.text.toString()
        if (title.isNotEmpty() && body.isNotEmpty()) {
                discussViewModel.uploadPost(file.toUri(), Post(title, body))
        } else {
            Toast.makeText(requireContext(), "One or more of the fields is empty", Toast.LENGTH_SHORT).show()
        }
    }



}