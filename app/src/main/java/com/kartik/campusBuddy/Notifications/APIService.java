package com.kartik.campusBuddy.Notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA4urEJ-E:APA91bEYjjgGwfVp_vlAwI__Xu_UUyyxL88dE45GHCdUfcseBWXbl5Gx_wHPByae1Vabl8tp2nMxvO9XOIaA9Lj6oRm5IMT-h9vf13Vqdpb2weENVRQ5EgMaRCyH6zsBHbePF3sZqhdh"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}