package com.soda1127.githubrepository.data.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.soda1127.githubrepository.R
import com.soda1127.githubrepository.helper.base.MinorHelper.toast
import io.reactivex.Flowable
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.net.UnknownHostException
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by gwaghuijong on 2018. 5. 16..
 */
object NetworkHelper {
    private const val TAG = "NetworkHelper"

    private const val serviceHostForSecurity = "https://api.github.com/"

    private val loggingInterceptor = HttpLoggingInterceptor()
    private val headerInterceptor by lazy {
        Interceptor { chain ->
            val request: Request = chain.request()
            val builder: Request.Builder = request.newBuilder().method(request.method(), request.body())
            builder.addHeader("User-Agent", "request")
            builder.addHeader("Authorization", "token cb2d34b815fbc50c78a0b193f4c8e12d8f3198b9")
            var response: okhttp3.Response? = null
            try {
                response =
                    chain.proceed(builder.build())
            } catch (e: UnknownHostException) {
                e.printStackTrace()
                toast(R.string.network_disconnected)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            response
        }
    }

    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val secureRetrofit = Retrofit.Builder()
        .baseUrl(serviceHostForSecurity)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    var apiSecureService = secureRetrofit.create(ApiService::class.java)

    interface ApiService {
        @GET
        fun getUrl(@Url url: String?): Flowable<String>

        @GET("/{resources}")
        fun getResources(@Path("resources") resource: String?): Flowable<String>

        @GET("/{resources}")
        fun getResources(@Path("resources") resource: String?, @QueryMap params: Map<String?, Any?>?): Flowable<String>

        @GET("/{resources}/{target}")
        fun getResourcesTarget(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?
        ): Flowable<String>

        @GET("/{resources}/{target}")
        fun getResourcesTarget(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @QueryMap params: Map<String?, Any?>?
        ): Flowable<String>

        @GET("/{resources}/{target}/{action}")
        fun getResourcesTargetAction(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action") action: String?
        ): Flowable<String>

        @GET("/{resources}/{target}/{action}")
        fun getResourcesTargetAction(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action") action: String?, @QueryMap params: Map<String?, Any?>?
        ): Flowable<String>

        @GET("/{resources}/{target}/{action1}/{action2}")
        fun getResourcesTargetAction(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action1") action1: String?, @Path(
                "action2"
            ) action2: String?
        ): Flowable<String>

        @POST
        fun postUrl(@Url url: String?): Flowable<String>

        @POST("/{resources}")
        fun postResources(@Path("resources") resource: String?): Flowable<String>

        @FormUrlEncoded
        @POST("/{resources}")
        fun postResources(@Path("resources") resource: String?, @FieldMap parameters: HashMap<String?, Any?>?): Flowable<String>

        @Multipart
        @POST("/{resources}")
        fun postReviewWithPart(
            @Path("resources") resource: String?,
            @Part imageList: List<MultipartBody.Part?>?,
            @Part("hospital_id") hospitalId: Int,
            @Part("price") price: Int,
            @Part("text") text: String?,
            @Part("doctor") doctor: String?,
            @Part("surgery_date") surgeryDate: String?,
            @Part("categories[]") categories: List<String>
        ): Flowable<String>

        @Multipart
        @POST("/{resources}")
        fun postTalkWithPart(
            @Path("resources") resource: String?,
            @Part imageList: List<MultipartBody.Part?>?,
            @Part("title") title: String?,
            @Part("text") text: String?
        ): Flowable<String>

        @POST("/{resources}/{target}")
        fun postResourcesTarget(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?
        ): Flowable<Void?>?

        @FormUrlEncoded
        @POST("/{resources}/{target}")
        fun postResourcesTarget(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @FieldMap parameters: HashMap<String?, Any?>?
        ): Flowable<String>

        @Multipart
        @POST("/{resources}/{target}")
        fun postMessageWithPart(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Part image: MultipartBody.Part?
        ): Flowable<String>

        @POST("/{resources}/{target}/{action}")
        fun postResourcesTargetAction(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action") action: String?
        ): Flowable<String>

        @POST("/{resources}/{target}/{action}")
        fun postResourcesTargetActionNoResponse(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action") action: String?
        ): Flowable<Response<Void?>?>?

        @FormUrlEncoded
        @POST("/{resources}/{target}/{action}")
        fun postResourcesTargetAction(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action") action: String?, @FieldMap parameters: HashMap<String?, Any?>?
        ): Flowable<String>

        @PATCH
        fun patchUrl(@Url url: String?): Flowable<String>

        @FormUrlEncoded
        @PATCH("/{resources}")
        fun patchResources(@Path("resources") resource: String?, @FieldMap parameters: HashMap<String?, Any?>?): Flowable<String>

        @FormUrlEncoded
        @PATCH("/{resources}/{target}")
        fun patchResourcesTarget(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @FieldMap parameters: HashMap<String?, Any?>?
        ): Flowable<String>

        @FormUrlEncoded
        @PATCH("/{resources}/{target}/{action}")
        fun patchResourcesTargetAction(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action") action: String?, @FieldMap parameters: HashMap<String?, Any?>?
        ): Flowable<String>

        @DELETE
        fun deleteUrl(@Url url: String?): Flowable<String>

        @DELETE
        fun deleteUrlNoResponse(@Url url: String?): Flowable<Response<Void?>?>?

        @DELETE("/{resources}")
        fun deleteResources(@Path("resources") resource: String?): Flowable<String>

        @FormUrlEncoded
        @HTTP(method = "DELETE", path = "/{resources}", hasBody = true)
        fun deleteResources(@Path("resources") resource: String?, @FieldMap parameters: HashMap<String?, Any?>?): Flowable<String>

        @DELETE("/{resources}/{target}")
        fun deleteResourcesTarget(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?
        ): Flowable<String>

        @DELETE("/{resources}/{target}")
        fun deleteResourcesTargetNoResponse(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?
        ): Flowable<Response<Void?>?>?

        @DELETE("/{resources}/{target}/{action}")
        fun deleteResourcesTargetAction(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action") action: String?
        ): Flowable<String>

        @DELETE("/{resources}/{target}/{action}")
        fun deleteResourcesTargetActionNoResponse(
            @Path("resources") resource: String?, @Path(
                "target"
            ) target: String?, @Path("action") action: String?
        ): Flowable<Response<Void?>?>?
    }
}