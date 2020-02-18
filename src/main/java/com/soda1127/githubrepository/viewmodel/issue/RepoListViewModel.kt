package com.soda1127.githubrepository.viewmodel.issue

import com.google.gson.JsonParser
import com.soda1127.githubrepository.basic.ActionHandler
import com.soda1127.githubrepository.basic.Type
import com.soda1127.githubrepository.basic.Variable
import com.soda1127.githubrepository.data.network.ApiHelper
import com.soda1127.githubrepository.mapper.DataMapper
import com.soda1127.githubrepository.model.Banner
import com.soda1127.githubrepository.model.Data
import com.soda1127.githubrepository.model.Repo
import com.soda1127.githubrepository.viewmodel.DataListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by leekijung on 2020. 2. 18..
 */

class RepoListViewModel : DataListViewModel() {

    val userName = Variable<String>()

    override fun getDataList(
        startHandler: ActionHandler?,
        successHandler: ActionHandler?,
        failureHandler: ActionHandler?,
        endHandler: ActionHandler?
    ) {
        startHandler?.invoke()
        userName.get()?.let { name ->
            ApiHelper.getUser(name)
                .subscribeOn(Schedulers.io())
                .map<MutableList<Data>> {
                    val list = mutableListOf<Data>()
                    val jsonObject = JsonParser().parse(it).asJsonObject
                    jsonObject.addProperty("type", Type.USER_CELL.type)
                    list.add(DataMapper.map(jsonObject))
                    list
                }.concatMap<MutableList<Data>> { list ->
                    val repoList = mutableListOf<Data>()
                    return@concatMap ApiHelper.getRepositories(name)
                        .subscribeOn(Schedulers.io())
                        .map<MutableList<Data>> {
                            val jsonArray = JsonParser().parse(it).asJsonArray
                            jsonArray.forEachIndexed { idx, element ->
                                element.asJsonObject.addProperty("type", Type.REPO_CELL.type)
                                repoList.add(DataMapper.map(element.asJsonObject))
                            }
                            repoList
                        }
                        .map<MutableList<Data>> {
                            val userList = it.sortedWith(compareByDescending { data -> (data as Repo).stargazersCount }).toMutableList()
                            list.addAll(userList)
                            list
                        }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this onResponseWith it
                }, {
                    it.printStackTrace()
                }).add()
        }
        endHandler?.invoke()
    }
}