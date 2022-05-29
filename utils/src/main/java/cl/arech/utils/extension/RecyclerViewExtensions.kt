package cl.arech.utils.extension

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onScrollEnd(action: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!recyclerView.canScrollVertically(1)) {
                action()
            }
        }
    })
}