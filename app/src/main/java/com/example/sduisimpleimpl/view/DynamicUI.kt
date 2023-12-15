package com.example.sduisimpleimpl.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sduisimpleimpl.model.data.SDUIModel
import com.example.sduisimpleimpl.model.data.SDUITextStyle

@Composable
fun DynamicUIComponent(componentId: String, model: SDUIModel, navController: NavController)
{

    Log.d("DynamicUIComponent", "componentId: $componentId")

    val uiComponent = model.reusableComponents[componentId]
    Log.d("DynamicUIComponent", "uiComponent: $uiComponent")

    when (uiComponent)
    {
        is SDUIModel.UIComponent.HeroText            ->
        {
            val textContent = model.data[uiComponent.textId] ?: ""
            val style =
                model.styles[SDUITextStyle.HeroText.type]?.toTextStyle() ?: TextStyle.Default

            Log.d("DynamicUIComponent", "HeroText: ${model.styles[SDUITextStyle.HeroText.type]}")

            Column(modifier = Modifier.fillMaxWidth().padding(top = 21.dp))
            {
                Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = textContent,
                        style = style,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

            }

        }

        is SDUIModel.UIComponent.StandardText        ->
        {
            val textContent = model.data[uiComponent.textId] ?: ""
            Log.d("DynamicUIComponent", "StandardText: ${textContent}")
            val style =
                model.styles[SDUITextStyle.StandardText.type]?.toTextStyle() ?: TextStyle.Default

            Log.d(
                "DynamicUIComponent",
                "StandardText: ${model.styles[SDUITextStyle.StandardText.type]}")

            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 21.dp, horizontal = 21.dp))
            {
                Column(modifier = Modifier.align(Alignment.CenterHorizontally))
                {
                    Text(text = textContent, style = style)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }


        }

        is SDUIModel.UIComponent.Button              ->
        {
            val buttonLabel = model.data[uiComponent.textId] ?: ""
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp, horizontal = 48.dp)
            ) {
                Button(
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp, pressedElevation = 10.dp),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        model.navigation[uiComponent.navigateTo]?.let { navRoute ->
                            navController.navigate(
                                navRoute)
                        }
                    },
                    content = { Text(text = buttonLabel) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White))
            }
        }
        //        is SDUIModel.UIComponent.LazyRowComponent -> {
        //            DynamicLazyRow(uiComponent,model,navController)
        //        }
        is SDUIModel.UIComponent.LazyColumnComponent ->
        {
            DynamicLazyColumn(uiComponent, model, navController)
        }

        else                                         ->
        {
        }
    }
}

@Composable
fun DynamicLazyRow(
    rowComponent: SDUIModel.UIComponent.LazyRowComponent,
    model: SDUIModel,
    navController: NavController,
)
{
    LazyRow {
        items(rowComponent.items) { itemId ->
            DynamicUIComponent(itemId, model, navController)
        }
    }
}

@Composable
fun DynamicLazyColumn(
    columnComponent: SDUIModel.UIComponent.LazyColumnComponent,
    model: SDUIModel,
    navController: NavController,
)
{
    LazyColumn {
        items(columnComponent.items) { itemId ->
            DynamicUIComponent(itemId, model, navController)
        }
    }
}


fun SDUIModel.Style.toTextStyle(): TextStyle
{
    return TextStyle(
        color = Color(android.graphics.Color.parseColor(color)),
        fontSize = textSize.sp,
    )
}
