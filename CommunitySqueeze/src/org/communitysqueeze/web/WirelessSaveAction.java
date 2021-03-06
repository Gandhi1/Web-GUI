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
package org.communitysqueeze.web;

import org.apache.log4j.Logger;

/**
 * @author Clive Messer <clive.m.messer@gmail.com>
 *
 */
public class WirelessSaveAction extends WirelessAction {

	private static final long serialVersionUID = -4796404325008094804L;
	
	private final static Logger LOGGER = Logger.getLogger(WirelessSaveAction.class);

	/**
	 * 
	 */
	public WirelessSaveAction() {
		
		super();
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("WirelessSaveAction()");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("validate()");
		}
		
		validate_();
	}
	
	/* (non-Javadoc)
	 * @see org.communitysqueeze.web.InterfaceAction#execute()
	 */
	public String execute() throws Exception {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("execute()");
		}
			
		String result = save();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("execute() returns " + result);
		}
		
		return result;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("save()");
		}
	
		String result = save_();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("save() returns " + result);
		}
		
		return result;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String saveAndReboot() throws Exception {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveAndReboot()");
		}
		
		String result = save();
		result = "reboot";
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveAndReboot() returns " + result);
		}
		
		return result;
	}
}
