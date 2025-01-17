package org.sorapointa.command.defaults.general

import org.sorapointa.CoreBundle
import org.sorapointa.Sorapointa
import org.sorapointa.command.Command
import org.sorapointa.command.CommandSender

class ListPlayer(private val sender: CommandSender) : Command(sender, ListPlayer) {

    companion object : Entry(
        name = "listplayer",
        helpKey = "sora.cmd.list.player.desc",
        alias = listOf("list"),
        permissionRequired = 1,
    )

    override suspend fun run() {
        val list = Sorapointa.getPlayerList()
        sender.sendMessage(
            CoreBundle.message(
                "sora.cmd.list.player.msg",
                list.size,
                list.joinToString { "${it.account.userName} (${it.basicComp.nickname}, UID:${it.uid})" },
                locale = sender.locale,
            ),
        )
    }
}
