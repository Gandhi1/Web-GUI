/**
 *  Community Squeeze Configuration Web-GUI
 *
 *  Copyright (C) 2013  Clive Messer <clive.m.messer@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.communitysqueeze.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.log4j.Logger;

/**
 * @author Clive Messer <clive.m.messer@gmail.com>
 *
 */
public class WebConfig {
	
	private final static Logger LOGGER = Logger.getLogger(WebConfig.class);

	private final static String SYSCONFIG_WEB_FILE = 
			"/etc/sysconfig/community-squeeze-web";
	
	private final static String CFG_TEMP_DIR = "TEMP_DIR";
	private final static String CFG_WIRED_INTERFACE_NAME = 
			"WIRED_INTERFACE";
	private final static String CFG_WIRELESS_INTERFACE_NAME = 
			"WIRELESS_INTERFACE";
	
	private static File TEMP_DIR = null;
	
	private static String WIRED_INTERFACE_NAME = "eth0";
	
	private static String WIRELESS_INTERFACE_NAME = "wlan0";
	
	static {
		
		init();
	}
	
	/**
	 * 
	 */
	private WebConfig() {
		
		super();
	}
	
	/**
	 * @return
	 */
	private final static String getValue(String line) {
		
		int index = line.indexOf("=");
		if (index > -1) {
			return line.substring(index + 1).trim(); 
		}
		
		return null;
	}
	
	/**
	 * @return
	 */
	public final static String getWiredInterfaceName() {
		
		return WIRED_INTERFACE_NAME;
	}
	
	/**
	 * @return
	 */
	public final static String getWirelessInterfaceName() {
		
		return WIRELESS_INTERFACE_NAME;
	}
	
	/**
	 * @return
	 */
	public final static File getTempDir() {
		
		return TEMP_DIR;
	}
	
	/**
	 * 
	 */
	private final static void init() {
		
		BufferedReader br = null;
		try {
			File configFile = new File(SYSCONFIG_WEB_FILE);
			if (configFile.exists()) {
				br = new BufferedReader(new FileReader(configFile));
				String line = null;
				while ((line = br.readLine()) != null) {
					line = line.trim();
					if (line.startsWith(CFG_TEMP_DIR)) {
						String value = getValue(line);
						if (value != null && value.length() > 0) {
							File tmpDir = new File(value);
							try {
								if (tmpDir.exists() && tmpDir.isDirectory()) {
									if (LOGGER.isDebugEnabled()) {
										LOGGER.debug("Setting dir for tmp file creation: " + tmpDir);
									}
									TEMP_DIR = tmpDir;
								}
							} catch (Exception e) {
								LOGGER.warn("Caught exception checking tmp dir: " + 
												tmpDir + " !", e);
							}
						}
					} else if (line.startsWith(CFG_WIRED_INTERFACE_NAME)) {
						String value = getValue(line);
						if (value.length() > 0) {
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Setting wired interface name: " + value);
							}
							WIRED_INTERFACE_NAME = value;
						}
					} else if (line.startsWith(CFG_WIRELESS_INTERFACE_NAME)) {
						String value = getValue(line);
						if (value.length() > 0) {
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Setting wireless interface name: " + value);
							}
							WIRELESS_INTERFACE_NAME = value;
						}
					} else {
						LOGGER.warn("Ignoring line: " + line);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Caught exception in static block initialising!", e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {}
			}
		}

		if (TEMP_DIR == null) {
			String tmpDir = "/tmp";
			File f = new File(tmpDir);
			try {
				if (f.exists() && f.isDirectory()) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Setting dir for tmp file creation: " + tmpDir);
					}
					TEMP_DIR = f;
				}
			} catch (Exception e) {}
		}
	}
}
