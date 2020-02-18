package com.soda1127.githubrepository.mapper

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.soda1127.githubrepository.basic.Type
import com.soda1127.githubrepository.helper.base.MinorHelper
import com.soda1127.githubrepository.model.Data
import com.soda1127.githubrepository.model.Repo
import com.soda1127.githubrepository.model.User
import kotlin.reflect.KClass

/**
 * Created by leekijung on 2019. 4. 21..
 */

class DataMapper {

    companion object {
        private var gson: Gson? = null
        private var type: String? = null

        fun map(json: JsonObject): Data {
            gson = MinorHelper.getGson()
            type = json.get("type").asString

            return when (type) {
                Type.REPO_CELL.type -> convertJsonType(json, Repo::class)
                Type.USER_CELL.type -> convertJsonType(json, User::class)
                else -> Data()
            }
        }

        private fun convertJsonType(json: JsonObject, clazz: KClass<out Data>): Data {
            return gson!!.fromJson(json.toString(), clazz.java)
        }
    }
}
