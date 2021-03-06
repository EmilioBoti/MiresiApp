package com.example.miresiapp.interfaces.apiendpoints

import com.example.miresiapp.businessLogic.forum.CategoryModel
import com.example.miresiapp.models.*
import com.example.miresiapp.models.forumModels.Comment
import com.example.miresiapp.models.forumModels.Forum
import com.example.miresiapp.models.forumModels.ForumComment
import com.example.miresiapp.models.forumModels.ForumModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @Headers("Content-type: application/json")
    @POST("api/v1/commentForum")
    fun commentForum(@Body comment: ForumComment): Call<Boolean>

    @GET("api/v1/forumComments/{forumId}")
    fun getCommentsForum(@Path("forumId") forumId: Int ): Call<MutableList<Comment>>

    @GET("api/v1/commentsReply/{fatherId}/{forumId}")
    fun getReplyComments(@Path("fatherId") fatherId: Int, @Path("forumId") forumId: Int ): Call<MutableList<Comment>>

    @Headers("Content-Type: application/json")
    @PUT("api/v1/updateUser/{id}")
    fun updateUser(@Path("id") id: Int, @Body map: HashMap<String, Any>): Call<Boolean>

    @Headers("Content-Type: application/json")
    @POST("api/v1/postComment")
    fun postComment(@Body comment: CommentModel): Call<CommentModel>

    @Headers("Content-Type: application/json")
    @POST("api/v1/publishForum")
    fun publishForum(@Body forum: Forum): Call<Boolean>

    @Headers("Content-Type: application/json")
    @POST("api/v1/createPost")
    fun insertPost(@Body post: Post): Call<Boolean>

    @Headers("Content-Type: application/json")
    @POST("api/v1/addfavourite")
    fun addToFavourite(@Body favourite: FavouriteModel): Call<Boolean>

    @Headers("Content-Type: application/json")
    @POST("api/v1/removefavourite")
    fun removeFromFavourite(@Body favourite: FavouriteModel): Call<Boolean>

    @Headers("Content-Type: application/json")
    @POST("api/v1/signup")
    fun register(@Body newUser: RegisterUser): Call<User>

    @GET("api/v1/comments/{id}/{limit}")
    fun getComments(@Path("id") id: Int,@Path("limit") limit: Int ): Call<MutableList<CommentModel>>

    @GET("api/v1/homeResi/{limit}")
    fun getHomeResis(@Path("limit") limit: Int ): Call<MutableList<Residence>>

    @GET("api/v1/forums")
    fun getForums(): Call<MutableList<ForumModel>>?

    @GET("api/v1/forum/{limit}")
    fun getForum(@Path("limit") limit: Int): Call<MutableList<ForumModel>>?

    @GET("api/v1/forums/{name}")
    fun getFilterForums(@Path("name") name: String): Call<MutableList<ForumModel>>?

    @GET("api/v1/categories")
    fun getAllCategories(): Call<MutableList<CategoryModel>>

    @GET("api/v1/posts")
    fun getPosts(): Call<MutableList<PostModel>>

    @GET("api/v1/residences/{city}")
    fun getResidences(@Path("city") city: String): Call<MutableList<Residence>>

    @GET("api/v1/resirooms/{id}")
    fun getRoomsResi(@Path("id") id: Int): Call<MutableList<Room>>

    @GET("api/v1/rooms/{limit}")
    fun getRooms(@Path("limit") limit: Int): Call<MutableList<Room>>

    @GET("api/v1/resi/{id}")
    fun getSingleResi(@Path("id") id: Int): Call<MutableList<Residence>>

    @GET("api/city/c/{name}")
    fun searchCity(@Path("name") name: String): Call<MutableList<City>>

    @GET("api/v1/cities/")
    fun getAllCities(): Call<MutableList<City>>

    @GET("api/v1/chats/{idUser}")
    fun userChats(@Path("idUser") idUser: Int): Call<MutableList<User>>

    @GET("api/v1/user/{id}")
    fun getUser(@Path("id") id: Int): Call<MutableList<User>>

    @GET("api/v1/user/{senderId}/{receiverId}")
    fun getUsers(@Path("id") senderId: Int, @Path("id") receiverId: Int): Call<MutableList<User>>

    @GET("api/v1/message/{from}/{to}")
    fun getSMS(@Path("from") from: Int, @Path("to") to: Int): Call<MutableList<Message>>
}