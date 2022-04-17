package com.dnieln7.testing.utils

import android.graphics.Canvas
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.dnieln7.testing.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class SwipeToDelete(private val onDelete: (Int) -> Unit) :
    ItemTouchHelper.SimpleCallback(0, LEFT or RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.layoutPosition

        onDelete(pos)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val background = ContextCompat.getColor(recyclerView.context, R.color.error)
        val foreground = ContextCompat.getColor(recyclerView.context, R.color.white)

        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        ).addPadding(TypedValue.COMPLEX_UNIT_DIP, 5F, 5F, 5F)
            .addCornerRadius(TypedValue.COMPLEX_UNIT_DIP, 5)
            .addActionIcon(R.drawable.ic_delete)
            .setActionIconTint(foreground)
            .addBackgroundColor(background)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}