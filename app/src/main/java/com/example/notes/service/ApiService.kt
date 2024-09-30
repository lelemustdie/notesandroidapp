import com.example.notes.service.UVResponse
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Header
import retrofit.http.Query

interface ApiService {
    @GET("/api/v1/uv")
    fun getUVIndex(
        @Query("lat") lat: Int,
        @Query("lng") lng: Int,
        @Query("dt") dt: String?,
        @Header("x-access-token") accessToken: String,
    ): Call<UVResponse>
}
