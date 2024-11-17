package com.github.raoe.codesnapidea.ui;

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.diagnostic.thisLogger
import org.jdesktop.swingx.JXTextField
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
    private var defaultSavePath: String
        get() = properties.getValue("codeSnap.defaultSavePath", "/default/path")
        set(value) = properties.setValue("codeSnap.defaultSavePath", value)
    private val myPanel: JPanel = JPanel(FlowLayout(FlowLayout.LEFT))
    private val mySavePathField: JTextField = JTextField(20)
    private val mySavePathButton: JButton = JButton("Browse...")
    private val mySavePathLabel: JLabel = JLabel("默认保存路径:")
    init {
        // 使用 defaultSavePath 属性来设置 JTextField 的值
        defaultSavePath = "/default/path"
        myPanel.add(mySavePathLabel)
        myPanel.add(mySavePathField)
        myPanel.add(mySavePathButton)
        mySavePathButton.addActionListener {
            val fileChooser = JFileChooser()
            fileChooser.selectedFile = File(mySavePathField.text)
            if (fileChooser.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                mySavePathField.text = fileChooser.selectedFile.absolutePath
                // 更新 defaultSavePath 属性
                defaultSavePath = fileChooser.selectedFile.absolutePath
            }
        }
    }

    fun getPanel(): JPanel {
        return myPanel
    }

    fun isModified(): Boolean {
        // 检查是否修改了设置
        return mySavePathField.text != defaultSavePath
    }

    fun apply() {
        // 应用设置的逻辑
        // 这里需要具体实现
        defaultSavePath = mySavePathField.text
        thisLogger().info("app apply>>>>>>>>>>>>>>>>>>>>>>>")
    }

    fun reset() {
        // 重置设置的逻辑
        // 这里需要具体实现
        mySavePathField.text = defaultSavePath
        thisLogger().info("app reset>>>>>>>>>>>>>>>>>>>>>>>")
    }

    fun saveSettings() {
        val properties = ServiceManager.getService(PropertiesComponent::class.java)
        properties.setValue("codeSnapSavePath", mySavePathField.text)
    }

    fun loadSettings() {
        val properties = ServiceManager.getService(PropertiesComponent::class.java)
        mySavePathField.text = properties.getValue("codeSnapSavePath", "defaultSavePath")
    }
}
