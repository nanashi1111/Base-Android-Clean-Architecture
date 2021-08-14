package com.dtv.starter.presenter.utils.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.Resources
import android.graphics.Rect
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

fun View.beInvisibleIf(beInvisible: Boolean) = if (beInvisible) beInvisible() else beVisible()

fun View.beVisibleIf(beVisible: Boolean) = if (beVisible) beVisible() else beGone()

fun View.beGoneIf(beGone: Boolean) = beVisibleIf(!beGone)

fun View.beInvisible() {
    visibility = View.INVISIBLE
}

fun View.beVisible() {
    visibility = View.VISIBLE
}

fun View.beGone() {
    visibility = View.GONE
}

fun View.beGoneWithAnimation() {
    animate().translationX(-width.toFloat()).alpha(0f).setDuration(ANIM_DURATION)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                beGone()
            }
        })
}

fun View.disappear(duration: Long) {
    animate().alpha(0f).setDuration(duration).setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
            beGone()
        }

        override fun onAnimationCancel(p0: Animator?) {
        }

        override fun onAnimationStart(p0: Animator?) {
        }
    })
}

fun View.appear(duration: Long) {
    animate().alpha(1f).setDuration(duration).setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {

        }

        override fun onAnimationCancel(p0: Animator?) {
        }

        override fun onAnimationStart(p0: Animator?) {
            beVisible()
        }
    })
}

fun View.onGlobalLayout(callback: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            callback()
        }
    })
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE

fun View.performHapticFeedback() = performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

fun View.listenSoftKeyboard(callback: (() -> Unit)? = null) {
    this.viewTreeObserver.addOnGlobalLayoutListener {
        val r = Rect()
        getWindowVisibleDisplayFrame(r)
        val heightDiff: Int = rootView.height - r.bottom
        if (heightDiff < rootView.height * 0.15) {
            callback?.invoke()
        } else {

        }
    }
}

const val ANIM_DURATION = 250L

val Float.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


fun WebView.setup() {
    val settings: WebSettings = getSettings()

    settings.javaScriptEnabled = true
    setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY)

    getSettings().setBuiltInZoomControls(true)
    getSettings().setUseWideViewPort(true)
    getSettings().setLoadWithOverviewMode(true)
}

fun ViewPager2.reduceDragSensitivity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * 8)       // "8" was obtained experimentally
}

