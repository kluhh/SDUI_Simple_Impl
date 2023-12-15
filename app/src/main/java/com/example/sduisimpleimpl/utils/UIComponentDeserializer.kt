package com.example.sduisimpleimpl.utils

import android.util.Log
import com.example.sduisimpleimpl.model.data.SDUIModel
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class UIComponentDeserializer : JsonDeserializer<SDUIModel.UIComponent>
{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): SDUIModel.UIComponent
    {
        Log.d("UIComponentDeserializer", "Executing...")



        val jsonObject = json?.asJsonObject

        val type = jsonObject?.get("type")?.asString

        Log.d("UIComponentDeserializer", "Deserializing component type: $type")

        return when (type)
        {
            "HeroText"       -> Gson().fromJson(json, SDUIModel.UIComponent.HeroText::class.java)
            "StandardText"       -> Gson().fromJson(json, SDUIModel.UIComponent.StandardText::class.java)
            "Button"     -> Gson().fromJson(json, SDUIModel.UIComponent.Button::class.java)
            "LazyRow"    -> Gson().fromJson(
                json,
                SDUIModel.UIComponent.LazyRowComponent::class.java)

            "LazyColumn" -> Gson().fromJson(
                json,
                SDUIModel.UIComponent.LazyColumnComponent::class.java)

            else         ->
            {
                throw JsonParseException("Unknown type in UIComponent")
            }
        }

    }

}