package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.OverviewViewModel.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter

/*
 * Binding Adapters are annotated methods used to
   create custom setters for custom properties of your view.
 * Usually when you set an attribute in your XML using the code: android:text="Sample Text",
   the Android system automatically looks for a setter with the same name as the text attribute,
   which is set by the setText(String: text) method.
 * The setText(String: text) method is a setter method for some views
   provided by the Android Framework.
 * Similar behavior can be customized using the binding adapters;
 * You can provide a custom attribute and custom logic
   that will be called by the Data binding library.
 */

// The @BindingAdapter annotation takes the attribute name as its parameter.
// In the bindImage method, the first method parameter is the type of the target View and
// the second is the value being set to the attribute.
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                    data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MarsApiStatus?) {

    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {
            statusImageView.visibility = View.GONE
        }
    }

}