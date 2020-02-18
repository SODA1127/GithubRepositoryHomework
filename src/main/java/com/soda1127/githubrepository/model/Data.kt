package com.soda1127.githubrepository.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import com.soda1127.githubrepository.basic.DataHandler
import com.soda1127.githubrepository.basic.Type
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

/**
 * Created by leekijung on 2020. 1. 28..
 */

@Parcelize
open class Data(
    open var id: Long = 0,
    open var type: String = Type.EMPTY_CELL.type,
    open var url: String = "null"
) : Parcelable {

    @IgnoredOnParcel
    var handler: DataHandler = { }
    @IgnoredOnParcel
    var detailHandler: DataHandler = { }
    @IgnoredOnParcel
    var deleteHandler: DataHandler = { }

    fun run() = handler.invoke(this)

    fun runDetail() = detailHandler.invoke(this)

    fun runDelete() = deleteHandler.invoke(this)

    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true
        val data = other as Data
        return data.id == this.id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + handler.hashCode()
        result = 31 * result + detailHandler.hashCode()
        return result
    }
}

