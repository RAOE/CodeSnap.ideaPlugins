package com.github.raoe.codesnapidea.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import javax.swing.Icon

/**
 *  ClassName：PopupDialogAction
Package:com.github.raoe.codesnapidea.actions
@DATE:06/11/2024 10:34 pm
@Author:XuYuanFeng
TODO:
 */

class PopupDialogAction: AnAction {
    constructor() : super() {
        // This default constructor is used by the IntelliJ Platform framework to instantiate this class based on plugin.xml
        // declarations. Only needed in `PopupDialogAction` class because a second constructor is overridden.
    }

    constructor(text: String?, description: String?, icon: Icon?) : super(text, description, icon) {
        // This constructor is used to support dynamically added menu actions.
        // It sets the text, description to be displayed for the menu item.
        // Otherwise, the default AnAction constructor is used by the IntelliJ Platform.
    }

    override fun actionPerformed(event: AnActionEvent) {
        val editor: Editor? = event.getData(CommonDataKeys.EDITOR)
        val project: Project? = event.getData(CommonDataKeys.PROJECT)
        val selectedText: String? = editor?.selectionModel?.selectedText
        val message = StringBuilder()
        if (!selectedText.isNullOrEmpty()) {
            message.append(selectedText).append(" Selected123!")
        } else {
            message.append("No text selected123!")
        }
        val title = "Selection Info"
        val icon: Icon = Messages.getInformationIcon()
        Messages.showMessageDialog(
            project,
            message.toString(),
            title,
            icon
        )
    }

    override fun update(event: AnActionEvent) {
        // Set the availability based on whether a project is open
        val project: Project? = event.project
        event.presentation.isEnabledAndVisible = project != null
    }

}