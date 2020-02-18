package com.soda1127.githubrepository.viewmodel

import com.soda1127.githubrepository.basic.ActionHandler
import com.soda1127.githubrepository.basic.Variable
import com.soda1127.githubrepository.model.Data

/**
 * Created by leekijung on 2020. 1. 28..
 */

open class DataListViewModel : BaseViewModel() {
    var dataList: MutableList<Data> = mutableListOf()
    var dataListToAdd: Variable<MutableList<Data>> = Variable()

    open fun getDataList(
        startHandler: ActionHandler? = null,
        successHandler: ActionHandler? = null,
        failureHandler: ActionHandler? = null,
        endHandler: ActionHandler? = null
    ) {
    }

    protected fun onResponseWith(data: Data) = this add data

    protected infix fun onResponseWith(dataList: List<Data>) = this addList dataList

    protected infix fun add(Data: Data) {
        dataList.add(Data)
        changeData()
    }

    protected fun add(index: Int, Data: Data) {
        dataList.add(index, Data)
        changeData()
    }

    protected operator fun set(index: Int, Data: Data) {
        dataList[index] = Data
        changeData()
    }

    protected infix fun remove(index: Int) {
        dataList.removeAt(index)
        changeData()
    }

    protected infix fun remove(data: Data) {
        dataList.remove(data)
        changeData()
    }

    private infix fun addList(list: List<Data>) {
        dataList.addAll(list)
        changeData()
    }

    private fun clearList() {
        if (dataList.isEmpty()) return
        dataList.clear()
        changeData()
    }

    private fun changeData() = dataListToAdd.set(mutableListOf<Data>().apply { addAll(dataList) })

    fun refresh(endHandler: ActionHandler) =
        getDataList(successHandler = { clearList() }, endHandler = endHandler)

    open fun clearHandler(dataList: List<Data>) {
        dataList.forEach {
            it.handler = { }
            it.detailHandler = { }
            it.deleteHandler = { }
        }
    }

    fun clearDisposable() {
        compositeSubscription.clear()
    }
}
