package com.github.raoe.codesnapidea.ui;

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.diagnostic.thisLogger
import java.awt.FlowLayout
import java.io.File
import java.util.*
import javax.swing.*

/**
 * ClassName：CodeSnapSettingsUI
 * Package:com.github.raoe.codesnapidea.ui
 *
 * @DATE:13/11/2024 9:38 pm
 * @Author:XuYuanFeng TODO:
 */
class CodeSnapSettingsUI {
    private val properties: PropertiesComponent = ServiceManager.getService(PropertiesComponent::class.java)
    var shortcutEnabled: Boolean
        get() = properties.getBoolean("codeSnap.shortcutEnabled", false)
        set(value) = properties.setValue("codeSnap.shortcutEnabled", value)

    var shortcut: String
        get() = properties.getValue("codeSnap.shortcut", "defaultShortcut")
        set(value) = properties.setValue("codeSnap.shortcut", value)

    var defaultSavePath: String
        get() = properties.getValue("codeSnap.defaultSavePath", "/default/path")
        set(value) = properties.setValue("codeSnap.defaultSavePath", value)


    private val myPanel: JPanel = JPanel(FlowLayout())
    private val myShortcutField: JCheckBox = JCheckBox("Enable Shortcut")
    private val myShortcutTextField: JTextField = JTextField(20)
    private val mySavePathField: JTextField = JTextField(20)
    private val mySavePathButton: JButton = JButton("Browse...")
    init {
//        myPanel.add(myShortcutField)
//        myPanel.add(myShortcutTextField)
        myPanel.add(mySavePathField)
        myPanel.add(mySavePathButton)
        mySavePathButton.addActionListener {
            val fileChooser = JFileChooser()
            fileChooser.selectedFile = File(mySavePathField.text)
            if (fileChooser.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                mySavePathField.text = fileChooser.selectedFile.absolutePath
            }
        }
    }

    fun getPanel(): JPanel {
        return myPanel
    }

    fun isModified(): Boolean {
        // 检查是否修改了设置
        return myShortcutField.isSelected != shortcutEnabled ||
                myShortcutTextField.text != shortcut ||
                mySavePathField.text != defaultSavePath
    }

    fun apply() {
        // 应用设置的逻辑
        // 这里需要具体实现
        shortcutEnabled = myShortcutField.isSelected
        shortcut = myShortcutTextField.text
        defaultSavePath = mySavePathField.text
        thisLogger().info("app apply>>>>>>>>>>>>>>>>>>>>>>>")
    }

    fun reset() {
        // 重置设置的逻辑
        // 这里需要具体实现
        myShortcutField.isSelected = shortcutEnabled
        myShortcutTextField.text = shortcut
        mySavePathField.text = defaultSavePath
        thisLogger().info("app reset>>>>>>>>>>>>>>>>>>>>>>>")
    }

    fun saveSettings() {
        val properties = ServiceManager.getService(PropertiesComponent::class.java)
        properties.setValue("codeSnapShortcut", myShortcutTextField.text)
        properties.setValue("codeSnapSavePath", mySavePathField.text)
    }

    fun loadSettings() {
        val properties = ServiceManager.getService(PropertiesComponent::class.java)
        myShortcutTextField.text = properties.getValue("codeSnapShortcut", "defaultShortcut")
        mySavePathField.text = properties.getValue("codeSnapSavePath", "defaultSavePath")
    }
}
