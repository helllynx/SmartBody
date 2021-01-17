//package org.helllynx.smartbody.ui.settings.personal
//
//
//import android.content.Context
//import android.util.AttributeSet
//import android.widget.LinearLayout
//import androidx.core.content.getSystemService
//import org.helllynx.smartbody.databinding.PersonalInfoCardBinding
//import org.helllynx.smartbody.utils.attr
//import org.helllynx.smartbody.utils.getDrawableCompat
//
//class PersonalInfoCard @JvmOverloads constructor(
//        context: Context,
//        attrs: AttributeSet? = null,
//        defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {
//    private val binding = PersonalInfoCardBinding.inflate(requireNotNull(context.getSystemService()), this)
//
//    var value: CharSequence?
//        get() = binding.value.text
//        set(value) {
//            binding.value.text = value
//        }
//
//    var title: CharSequence?
//        get() = binding.title.text
//        set(value) {
//            binding.title.text = value
//        }
//
//    var onClickListener: (() -> Unit)? = null
//
//    init {
//        binding..setOnClickListener { onClickListener?.invoke() }
//
//        context.apply {
//            binding.surfaceLayout.background =
//                    getDrawableCompat(attr(android.R.attr.selectableItemBackground).resourceId)
//        }
//    }
//}
