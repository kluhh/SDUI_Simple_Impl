package com.example.sduisimpleimpl.model.data

data class SDUIModel(
    val screens: Map<String, Screen>,
    val reusableComponents: Map<String, UIComponent>,
    val data: Map<String, String>,
    val navigation: Map<String, String>,
    val styles: Map<String, Style>,
)
{
    data class Screen(
        val uiComponents: List<String>,
    )

    sealed class UIComponent
    {
        data class HeroText(val textId: String) : UIComponent()
        data class StandardText(val textId: String) : UIComponent()
        data class Button(val textId: String, val navigateTo: String) : UIComponent()
        data class LazyRowComponent(val items: List<String>) : UIComponent()
        data class LazyColumnComponent(val items: List<String>) : UIComponent()
    }

    data class Style(
        val color: String,
        val textSize: Int,
    )
}

enum class SDUITextStyle(val type: String)
{
    HeroText("heroText"),
    StandardText("standardText"),
}
