package com.tm.example.mymvp_retrofit_rxjavatest;

import com.tm.example.mymvp_retrofit_rxjavatest.entity.BookBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tian on 2017/2/13.
 */

public interface IRetrofitService {

    //Retrofit普通用法
    @GET("book/search")
    Call<BookBean> getSearchBook(@Query("q") String name,
                                 @Query("tag") String tag,
                                 @Query("start") int start,
                                 @Query("count") int count
                                 );

    //RxJava用
    @GET("book/search")
    Observable<BookBean> getSearchBook2(@Query("q") String name,
                                       @Query("tag") String tag,
                                       @Query("start") int start,
                                       @Query("count") int count
                                        );


    /*
    @Query(GET请求):用于在url后拼接上参数

    @GET("book/search")
    Call<Book> getSearchBook(@Query("q") String name);

    相当于：

    @GET("book/search?q=name")
    Call<Book> getSearchBook();
    */

    /*
    @QueryMap(GET请求):

    当然如果入参比较多，就可以把它们都放在Map中，例如：

    @GET("book/search")
    Call<Book> getSearchBook(@QueryMap Map<String, String> options);
    */

    /*
    @Path(GET请求):

    用于替换url中某个字段，例如：

    @GET("group/{id}/users")
    Call<Book> groupList(@Path("id") int groupId);

    像这种请求接口，在group和user之间有个不确定的id值需要传入，就可以这种方法。我们把待定的值字段用{}括起来，当然 {}里的名字不一定就是id，可以任取，但需和@Path后括号里的名字一样。如果在user后面还需要传入参数的话，就可以用Query拼接上，比如：

    @GET("group/{id}/users")
    Call<Book> groupList(@Path("id") int groupId,@Query("sort") String sort);

    当我们调用这个方法时，假设我们groupId传入1，sort传入“2”，那么它拼接成的url就是group/1/users?sort=2，当然最后请求的话还会加上前面的baseUrl。
     */

    /*
    @Body(POST请求):

    可以指定一个对象作为HTTP请求体,比如：

    @POST("users/new")
    Call<User> createUser(@Body User user);
     */


    /*
    @Field(POST请求):

    用于传送表单数据：

    @FormUrlEncoded
    @POST("user/edit")
    Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);

    注意开头必须多加上@FormUrlEncoded这句注释，不然会报错。表单自然是有多组键值对组成，这里的first_name就是键，而具体传入的first就是值啦。
     */


    /*
    @Header/@Headers(POST请求):

    用于添加请求头部：

    @GET("user")
    Call<User> getUser(@Header("Authorization") String authorization)

    表示将头部Authorization属性设置为你传入的authorization；当然你还可以用@Headers表示,作用是一样的比如：

    @Headers("Cache-Control: max-age=640000")
    @GET("user")
    Call<User> getUser()

    当然你可以多个设置：

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("user")
    Call<User> getUser()
    */


}
