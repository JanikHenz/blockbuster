package fhnw.emoba.blockbuster

import BlockbusterModel
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.emoba.EmobaApp
import fhnw.emoba.blockbuster.ui.AppUI


object BlockbusterApp : EmobaApp {
    private lateinit var model: BlockbusterModel

    override fun initialize(activity: ComponentActivity) {
        model = BlockbusterModel;
    }

    @Composable
    override fun CreateUI() {
        AppUI(model)
    }

}