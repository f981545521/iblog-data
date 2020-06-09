## GIT

硬撤销
本地代码会直接变更为指定的提交版本，慎用

删除工作空间改动代码，撤销 commit，撤销 git add .

注意完成这个操作后，就恢复到了上一次的commit状态。

git reset --hard HEAD~1

如果仅仅是 commit 的消息内容填错了
输入

git commit --amend

进入 vim 模式，对 message 进行更改

还有一个 --mixed

git reset --mixed HEAD~1

意思是：不删除工作空间改动代码，撤销commit，并且撤销git add . 操作
这个为默认参数,git reset --mixed HEAD~1 和 git reset HEAD~1 效果是一样的。

