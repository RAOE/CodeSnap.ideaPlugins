module.exports = {
    extends: ['@commitlint/config-conventional'],
    rules: {
        // 在这里添加您的自定义规则
        'type-enum': [2, 'always', [
            'feat', 'fix', 'docs', 'style', 'refactor', 'perf', 'test', 'build', 'ci', 'chore', 'revert'
        ]],
        'type-case': [0],
        'type-empty': [0],
        'scope-empty': [0],
        'scope-case': [0],
        'subject-full-stop': [0],
        'subject-case': [0, 'never'],
        'header-max-length': [2, 'always', 72]
    }
};