module.exports = {
    extends: ['@commitlint/config-conventional'],
    rules: {
        // 自定义规则
        'body-leading-blank': [1, 'always'], // 确保body开始有一个空行
        'footer-leading-blank': [1, 'always'], // 确保footer开始有一个空行
        // 其他规则...
    }
}