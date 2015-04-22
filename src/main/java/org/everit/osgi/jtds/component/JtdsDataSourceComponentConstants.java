/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.osgi.jtds.component;

import net.sourceforge.jtds.jdbc.DefaultProperties;
import net.sourceforge.jtds.jdbc.Driver;

/**
 * Configuration attributes and other constant values of JtdsDataSourceComponent.
 */
public final class JtdsDataSourceComponentConstants {

  public static final String DEFAULT_APPNAME = DefaultProperties.APP_NAME;

  public static final boolean DEFAULT_AUTOCOMMIT = true;

  public static final int DEFAULT_BUFFERMAXMEMORY = 1024;

  public static final int DEFAULT_BUFFERMINPACKETS = 8;

  public static final boolean DEFAULT_CACHEMETA = false;

  public static final boolean DEFAULT_LASTUPDATECOUNT = true;

  public static final long DEFAULT_LOBBUFFER = 32 * 1024;

  public static final String DEFAULT_MACADDRESS = DefaultProperties.MAC_ADDRESS;

  public static final int DEFAULT_MAXSTATEMENTS = 500;

  public static final boolean DEFAULT_NAMEDPIPE = false;

  public static final String DEFAULT_PROCESSID = DefaultProperties.PROCESS_ID;

  public static final String DEFAULT_PROGNAME = DefaultProperties.PROG_NAME;

  public static final boolean DEFAULT_SENDSTRINGPARAMETERSASUNICODE = true;

  public static final boolean DEFAULT_SOKEEPALIVE = false;

  public static final int DEFAULT_SOTIMEOUT = 0;

  public static final boolean DEFAULT_TCPNODELAY = true;

  public static final boolean DEFAULT_USECURSORS = false;

  public static final boolean DEFAULT_USEJCIFS = false;

  public static final boolean DEFAULT_USEKERBEROS = false;

  public static final boolean DEFAULT_USELOBS = true;

  public static final boolean DEFAULT_USENTLMV2 = false;

  public static final boolean DEFAULT_XAEMULATION = true;

  public static final String FACTORY_PID = "org.everit.osgi.jtds.component.JtdsDataSourceComponent";

  public static final int OPTION_VALUE_SERVERTYPE_SQLSERVER = Driver.SQLSERVER;

  public static final int OPTION_VALUE_SERVERTYPE_SYBASE = Driver.SYBASE;

  public static final String OPTION_VALUE_SSL_AUTHENTICATE = "authenticate";

  public static final String OPTION_VALUE_SSL_OFF = "off";

  public static final String OPTION_VALUE_SSL_REQUEST = "request";

  public static final String OPTION_VALUE_SSL_REQUIRE = "require";

  public static final String PROP_APPNAME = "appName";

  public static final String PROP_AUTOCOMMIT = "autoCommit";

  public static final String PROP_BATCHSIZE = "batchSize";

  public static final String PROP_BINDADDRESS = "bindAddress";

  public static final String PROP_BUFFERDIR = "bufferDir";

  public static final String PROP_BUFFERMAXMEMORY = "bufferMaxMemory";

  public static final String PROP_BUFFERMINPACKETS = "bufferMinPackets";

  public static final String PROP_CACHEMETA = "cacheMetaData";

  public static final String PROP_CHARSET = "charset";

  public static final String PROP_DATABASENAME = "databaseName";

  public static final String PROP_DESCRIPTION = "description";

  public static final String PROP_DOMAIN = "domain";

  public static final String PROP_INSTANCE = "instance";

  public static final String PROP_LANGUAGE = "language";

  public static final String PROP_LASTUPDATECOUNT = "lastUpdateCount";

  public static final String PROP_LOBBUFFER = "lobBuffer";

  public static final String PROP_LOGFILE = "logFile";

  public static final String PROP_LOGINTIMEOUT = "loginTimeout";

  public static final String PROP_MACADDRESS = "macAddress";

  public static final String PROP_MAXSTATEMENTS = "maxStatements";

  public static final String PROP_NAMEDPIPE = "namedPipe";

  public static final String PROP_PACKETSIZE = "packetSize";

  public static final String PROP_PASSWORD = "password";

  public static final String PROP_PORTNUMBER = "port";

  public static final String PROP_PREPARESQL = "prepareSQL";

  public static final String PROP_PROCESSID = "processId";

  public static final String PROP_PROGNAME = "progName";

  public static final String PROP_SENDSTRINGPARAMETERSASUNICODE = "sendStringParametersAsUnicode";

  public static final String PROP_SERVERNAME = "serverName";

  public static final String PROP_SERVERTYPE = "serverType";

  public static final String PROP_SOKEEPALIVE = "socketKeepAlive";

  public static final String PROP_SOTIMEOUT = "socketTimeout";

  public static final String PROP_SSL = "ssl";

  public static final String PROP_TCPNODELAY = "tcpNoDelay";

  public static final String PROP_TDS = "tds";

  public static final String PROP_USECURSORS = "useCursors";

  public static final String PROP_USEJCIFS = "useJCIFS";

  public static final String PROP_USEKERBEROS = "useKerberos";

  public static final String PROP_USELOBS = "useLOBs";

  public static final String PROP_USENTLMV2 = "useNTLMv2";

  public static final String PROP_USER = "user";

  public static final String PROP_WSID = "wsid";

  public static final String PROP_XAEMULATION = "xaEmulation";

  private JtdsDataSourceComponentConstants() {
  }
}
