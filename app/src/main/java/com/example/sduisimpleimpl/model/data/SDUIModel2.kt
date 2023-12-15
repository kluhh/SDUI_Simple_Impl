package com.example.sduisimpleimpl.model.data

data class SDUIModel2(
    val screens: Map<String, Screen>,
    val reusableComponents: Map<String, UIComponent>,
    val data: Map<String, String>,
    val navigation: Map<String, String>,
    val styles: Map<String, Style>
) {
    data class Screen(
        val uiComponents: List<String>
    )

    sealed class UIComponent {
        data class Text(
            val textId: String,
            val responsiveDesignInfo: ResponsiveDesignInfo? = null,
            val accessibilityInfo: AccessibilityInfo? = null
        ) : UIComponent()

        data class Button(
            val textId: String,
            val navigateTo: String,
            val animationInfo: AnimationInfo? = null,
            val responsiveDesignInfo: ResponsiveDesignInfo? = null,
            val accessibilityInfo: AccessibilityInfo? = null
        ) : UIComponent()

        // ... other component types ...

        data class AccessibilityInfo(
            val contentDescription: String
        )

        data class AnimationInfo(
            val type: String, // e.g., "fadeIn", "slide"
            val duration: Long
        )

        data class ResponsiveDesignInfo(
            val minWidth: Int,
            val maxWidth: Int
            // Other responsive design related properties
        )
    }

    data class Style(
        val color: String,
        val textSize: Int,
        val fontFamily: String? = null,
        val letterSpacing: Float? = null,
        val lineHeight: Float? = null,
        val stateStyles: Map<String, StateStyle>? = null,
        val shadow: ShadowStyle? = null,
        val border: BorderStyle? = null,
        val responsiveStyles: Map<String, ResponsiveStyle>? = null
    ) {
        data class StateStyle(
            val color: String? = null,
            val textSize: Int? = null
            // Additional properties for state-specific styles
        )

        data class ShadowStyle(
            val color: String,
            val offsetX: Float,
            val offsetY: Float,
            val radius: Float
        )

        data class BorderStyle(
            val color: String,
            val width: Float,
            val cornerRadius: Float
        )

        data class ResponsiveStyle(
            val minWidth: Int,
            val maxWidth: Int,
            val style: Style
        )
    }
}
