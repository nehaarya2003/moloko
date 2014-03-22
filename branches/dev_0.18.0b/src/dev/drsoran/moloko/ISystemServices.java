/* 
 *	Copyright (c) 2013 Ronny R�hricht
 *
 *	This file is part of Moloko.
 *
 *	Moloko is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	Moloko is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with Moloko.  If not, see <http://www.gnu.org/licenses/>.
 *
 *	Contributors:
 * Ronny R�hricht - implementation
 */

package dev.drsoran.moloko;

import android.os.Handler;
import dev.drsoran.moloko.connection.IConnectionService;
import dev.drsoran.moloko.event.ISystemEventService;
import dev.drsoran.rtm.ILog;


public interface ISystemServices
{
   ILog Log();
   
   
   
   Handler getHandler();
   
   
   
   IHandlerTokenFactory getHandlerTokenFactory();
   
   
   
   IExecutorService getExecutorService();
   
   
   
   IConnectionService getConnectionService();
   
   
   
   ISystemEventService getSystemEvents();
   
   
   
   IHandlerToken acquireHandlerToken();
}