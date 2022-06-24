package com.bunnytechsolutions.jebsamplepluginkt

import com.pnfsoftware.jeb.core.*
import com.pnfsoftware.jeb.util.logging.*
import java.io.File

class PluginEntry : AbstractEnginesPlugin() {
    private val logger: ILogger = GlobalLog.getLogger(PluginEntry::class.java)

    override fun load(iEnginesContext: IEnginesContext?) {
        super.load(iEnginesContext)
        logger.debug("%s - Loading plug-in", TAG)
    }

    // This code actually executes when you run your plugin
    override fun execute(ctx: IEnginesContext, map: Map<String?, String?>?) {
        // Get open projects
        val jebProjects: List<IRuntimeProject> = ctx.projects
        // Check if there are any open projects
        if (jebProjects.isEmpty()) {
            logger.warn("%s - There are no opened projects", TAG)
            return
        }
        // Select top project
        val project: IRuntimeProject = jebProjects[0]
        logger.info("%s - Project name: %s", TAG, project.name)
        val workDir: String = File(project.name).parent + File.separator
        logger.info("%s - Work Dir: %s", TAG, workDir)
    }

    // This just runs when JEB needs the plugin metadata, eg: name which is currently set to TAG (not recommended but gets the point across)
    override fun getPluginInformation(): IPluginInformation {
        return PluginInformation(
            TAG,
            "A test of Kotlin JEB plug-ins",
            "BRUHItsABunny",
            Version.create(1, 0, 0)
        )
    }

    companion object {
        private const val TAG = "com.bunnytechsolutions.jebsamplepluginkt.PluginEntry"
    }
}