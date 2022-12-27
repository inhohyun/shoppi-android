package com.shoppi.shoppi.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.json.JSONObject

class HoemFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_product_detail)

            //context가 nullable일 수 있기 때문에 ?와 함께 사용
            context?.assets?.open("home.json")?.use{ inputStream ->
                val size = inputStream.available()
                val bytes = ByteArray(size)
                inputStream.read(bytes)
                val homeData = String(bytes)
                Log.d("homeData",homeData)
            }

        }
        val assetLoader = AssetLoader()
        val homeData = assetLoader.getJsonString(requireContext(), "home.json")
        Log.d("homeData", homeData ?: "")//null일경우 빈문자 출력
        if(!homeData.isNullOrEmpty()){
            val jsonObject = JSONObject(homeData)
            val title = jsonObject.getJSONObject("title")
            val text = title.getString("text")
            val iconUrl = title.getString("icon_url")
            val titleValue = Title(text, iconUrl)
            titleValue.text

//            val topBanners = jsonObject.getJSONArray("top_banners")
//            val firstBanner = topBanners.getJSONObject(0)
//            val label = firstBanner.getString("label")
//            val productDetail = firstBanner.getJSONObject("product_detail")
//            val price = productDetail.getInt("price")
//
//            Log.d("title", "text=${text}, iconUrl=${iconUrl}")
//            Log.d("firstBanner", "label=${label}, price=${price}")

        }
    }
}