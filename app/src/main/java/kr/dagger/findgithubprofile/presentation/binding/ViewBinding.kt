package kr.dagger.findgithubprofile.presentation.binding

import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.dagger.daggerhiltnetworkconnection.R

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        if(!text.isNullOrEmpty()) {
            Toast.makeText(view.context, text, Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(view.context, "널임", Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if(shouldBeGone) {
            View.GONE
        }else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("loadUrl")
    fun bindUrl(webView: WebView, url: String?) {
        url?.let { webView.loadUrl(it) }
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun bindLoadImage(view: ImageView, url: String?) {
        when(url.isNullOrEmpty()) {
            true -> view.setImageResource(R.drawable.ic_img_github)
            false -> {
                Glide.with(view.context)
                    .load(url)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .error(ContextCompat.getDrawable(view.context, R.drawable.ic_img_github))
                    .placeholder(ContextCompat.getDrawable(view.context, R.drawable.ic_img_github))
                    .circleCrop()
                    .into(view)
            }
        }
    }
}