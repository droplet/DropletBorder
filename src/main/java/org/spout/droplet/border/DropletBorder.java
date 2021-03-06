/*
 * This file is part of DropletBorder.
 *
 * Copyright (c) 2012 Spout LLC <http://www.spout.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.spout.droplet.border;

import java.util.logging.Level;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.plugin.Plugin;

public class DropletBorder extends Plugin implements Listener {
	private BorderConfiguration config;

	@Override
	public void onEnable() {
		config = new BorderConfiguration(this);
		config.load();
		AnnotatedCommandExecutorFactory.create(new BorderCommands(this));
		getEngine().getEventManager().registerEvents(this, this);
		log("DropletBorder enabled");
	}

	@Override
	public void onDisable() {
		config.save();
		log("DropletBorder disabled");
	}

	public void log(String text) {
		log(Level.INFO, text);
	}

	public void log(Level level, String text) {
		getLogger().log(level, text);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().add(BorderComponent.class);
	}
}
