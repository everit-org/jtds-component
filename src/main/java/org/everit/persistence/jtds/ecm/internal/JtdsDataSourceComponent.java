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
package org.everit.persistence.jtds.ecm.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Deactivate;
import org.everit.osgi.ecm.annotation.attribute.BooleanAttribute;
import org.everit.osgi.ecm.annotation.attribute.IntegerAttribute;
import org.everit.osgi.ecm.annotation.attribute.IntegerAttributeOption;
import org.everit.osgi.ecm.annotation.attribute.LongAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributeOption;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.component.ComponentContext;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.persistence.jtds.ecm.JtdsDataSourceComponentConstants;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

import aQute.bnd.annotation.headers.ProvideCapability;
import net.sourceforge.jtds.jdbcx.JtdsDataSource;

/**
 * Configurable component that registers {@link JtdsDataSource} as an OSGi service.
 */
@Component(componentId = JtdsDataSourceComponentConstants.SERVICE_FACTORY_PID,
    configurationPolicy = ConfigurationPolicy.FACTORY, label = "Everit jTDS DataSource",
    description = "Configurable component that instantiates JtdsDataSource and registers it as an "
        + "OSGi service based on DataSource, XADataSource and PoolingDataSource interfaces.")
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@StringAttributes({
    @StringAttribute(attributeId = Constants.SERVICE_DESCRIPTION, optional = true,
        label = "Service Description",
        description = "The description of this component configuration. It is used to easily "
            + "identify the service registered by this component.") })
public class JtdsDataSourceComponent {

  private String appName;

  private boolean autoCommit;

  private Integer batchSize;

  private String bindAddress;

  private String bufferDir;

  private int bufferMaxMemory;

  private int bufferMinPackets;

  private boolean cacheMetaData;

  private String charset;

  private String databaseName;

  private String description;

  private String domain;

  private String instance;

  private String language;

  private boolean lastUpdateCount;

  private long lobBuffer;

  private String logFile;

  private Integer loginTimeout;

  private String macAddress;

  private int maxStatements;

  private boolean namedPipe;

  private Integer packetSize;

  private String password;

  private Integer portNumber;

  private Integer prepareSql;

  private String processId;

  private String progName;

  private boolean sendStringParametersAsUnicode;

  private String serverName;

  private int serverType;

  private ServiceRegistration<?> serviceRegistration;

  private boolean socketKeepAlive;

  private int socketTimeout;

  private String ssl;

  private boolean tcpNoDelay;

  private String tds;

  private boolean useCursors;

  private boolean useJCIFS;

  private boolean useKerberos;

  private boolean useLOBs;

  private boolean useNTLMv2;

  private String user;

  private String wsid;

  private boolean xaEmulation;

  /**
   * Instantiates a {@link JtdsDataSource} and registers it as an OSGi service.
   */
  @Activate
  public void activate(final ComponentContext<JtdsDataSourceComponent> componentContext) {
    JtdsDataSource jtdsDataSource = new JtdsDataSource();

    updatePropertiesAToD(jtdsDataSource);
    updatePropertiesIToN(jtdsDataSource);
    updatePropertiesPToX(jtdsDataSource);

    Dictionary<String, Object> serviceProperties =
        new Hashtable<String, Object>(componentContext.getProperties());
    serviceRegistration = componentContext.registerService(
        new String[] { DataSource.class.getName(), XADataSource.class.getName(),
            ConnectionPoolDataSource.class.getName(), JtdsDataSource.class.getName() },
        jtdsDataSource, serviceProperties);
  }

  /**
   * Unregisters the {@link JtdsDataSource} OSGi service.
   */
  @Deactivate
  public void deactivate() {
    if (serviceRegistration != null) {
      serviceRegistration.unregister();
    }
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_APPLICATION_NAME,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_APP_NAME,
      priority = JtdsDataSourceAttributePriority.P09_APPLICATION_NAME, label = "Application name",
      description = "No practical use, it's displayed by Enterprise Manager or Profiler associated "
          + "with the connection.")
  public void setAppName(final String appName) {
    this.appName = appName;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_AUTO_COMMIT,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_AUTO_COMMIT,
      priority = JtdsDataSourceAttributePriority.P07_AUTO_COMMIT, label = "Auto commit",
      description = "The jTDS driver enables auto commit by default. Use this option to disable "
          + "auto commit for newly created connections.")
  public void setAutoCommit(final boolean autoCommit) {
    this.autoCommit = autoCommit;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_BATCH_SIZE, optional = true,
      priority = JtdsDataSourceAttributePriority.P10_BATCH_SIZE, label = "Batch size",
      description = "Controls how many statements are sent to the server in a batch. "
          + "The actual batch is broken up into pieces this large that are sent separately. "
          + "The reason for this is to avoid Sybase \"hangs\" caused by running out of space "
          + "with very large batches. The problem doesn't seem to occur with SQL Server, hence "
          + "the default limit of 0 (unlimited) in this case. (default - 0 for SQL Server; "
          + "1000 for Sybase)")
  public void setBatchSize(final Integer batchSize) {
    this.batchSize = batchSize;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_BIND_ADDRESS,
      optional = true,
      priority = JtdsDataSourceAttributePriority.P11_BIND_ADDRESS, label = "Bind address",
      description = "Specifies the local IP address to bind to for outgoing TCP/IP "
          + "connections to the database. Useful for multi-homed systems "
          + "(those with more than one external IP address) where the default IP address "
          + "picked by Java will not connect to the database. Currently has no effect when using "
          + "named pipes to connect to a database (see namedPipe). (default - determined by the "
          + "Java implementation; requires Java 1.4 or later)")
  public void setBindAddress(final String bindAddress) {
    this.bindAddress = bindAddress;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_BUFFER_DIR, optional = true,
      priority = JtdsDataSourceAttributePriority.P12_BUFFER_DIR, label = "Buffer dir",
      description = "Controls the destination where data is buffered to disk. See also "
          + "bufferMaxMemory and bufferMinPackets. "
          + "(default - System.getProperty(\"java.io.tmpdir\"))")
  public void setBufferDir(final String bufferDir) {
    this.bufferDir = bufferDir;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_BUFFER_MAX_MEMORY,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_BUFFER_MAX_MEMORY,
      priority = JtdsDataSourceAttributePriority.P13_BUFFER_MAX_MEMORY, label = "Buffer max memory",
      description = "Controls the global buffer memory limit for all connections (in kilobytes). "
          + "When the amount of buffered server response packets reaches this limit additional "
          + "packets are buffered to disk; there is however one exception: each Statement gets to "
          + "buffer at least <bufferMinPackets> to memory before this limit is enforced. "
          + "This means that this limit can and will usually be exceeded. Server responses "
          + "are buffered to disk only when a request is made on a Statement while another "
          + "Statement belonging to the same Connection still hasn't processed all its results. "
          + "These situations can be avoided in most cases by setting the useCursors property, "
          + "but this will also affect performance. See also bufferMinPackets.")
  public void setBufferMaxMemory(final int bufferMaxMemory) {
    this.bufferMaxMemory = bufferMaxMemory;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_BUFFER_MIN_PACKETS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_BUFFER_MIN_PACKETS,
      priority = JtdsDataSourceAttributePriority.P14_BUFFER_MIN_PACKETS,
      label = "Buffer min packets",
      description = "Controls the minimum number of packets per statement to buffer to memory. "
          + "Each Statement will buffer at least this many packets before being forced to use a "
          + "temporary file if the <bufferMaxMemory> is reached, to ensure good performance even "
          + "when one Statement caches a very large amount of data. Server responses are buffered "
          + "to disk only when a request is made on a Statement while another Statement belonging "
          + "to the same Connection still hasn't processed all its results. These situations "
          + "can be avoided in most cases by setting the useCursors property, but this will also "
          + "affect performance. See also bufferMaxMemory.")
  public void setBufferMinPackets(final int bufferMinPackets) {
    this.bufferMinPackets = bufferMinPackets;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_CACHE_META_DATA,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_CACHE_META_DATA,
      priority = JtdsDataSourceAttributePriority.P16_CACHE_META_DATA, label = "Cache metadata",
      description = "When used with prepareSQL=3, setting this property to true will cause the "
          + "driver to cache column meta data for SELECT statements. Caching the meta data will "
          + "reduce the processing overhead when reusing statements that return small result sets "
          + "that have many columns but may lead to unexpected errors if the database schema "
          + "changes after the statement has been prepared. Use with care. Only applicable to "
          + "SQL Server (there is no prepareSQL=3 mode for Sybase).")
  public void setCacheMetaData(final boolean cacheMetaData) {
    this.cacheMetaData = cacheMetaData;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_CHARSET, optional = true,
      priority = JtdsDataSourceAttributePriority.P15_CHARSET, label = "Charset",
      description = "Very important setting, determines the byte value to character mapping for "
          + "CHAR/VARCHAR/TEXT values. Applies for characters from the extended set "
          + "(codes 128-255). For NCHAR/NVARCHAR/NTEXT values doesn't have any effect since "
          + "these are stored using Unicode. (default - the character set the server was "
          + "installed with)")
  public void setCharset(final String charset) {
    this.charset = charset;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_DATABASE_NAME,
      optional = true, priority = JtdsDataSourceAttributePriority.P06_DATABASE_NAME,
      label = "Database name",
      description = "JDBC term: catalog (default - the user's default database).")
  public void setDatabaseName(final String databaseName) {
    this.databaseName = databaseName;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_DESCRIPTION, optional = true,
      priority = JtdsDataSourceAttributePriority.P17_DESCRIPTION, label = "Description")
  public void setDescription(final String description) {
    this.description = description;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_DOMAIN, optional = true,
      priority = JtdsDataSourceAttributePriority.P18_DOMAIN, label = "Domain",
      description = "Specifies the Windows domain to authenticate in. If present and the user name "
          + "and password are provided, jTDS uses Windows (NTLM) authentication instead of the "
          + "usual SQL Server authentication (i.e. the user and password provided are the "
          + "domain user and password). This allows non-Windows clients to log in to servers "
          + "which are only configured to accept Windoes authentication. If the domain parameter "
          + "is present but no user name and password are provided, jTDS uses its native "
          + "Single-Sign-On library and logs in with the logged Windows user's credentials "
          + "(for this to work one would obviously need to be on Windows, logged into a domain, "
          + "and also have the SSO library installed -- consult README.SSO in the distribution on "
          + "how to do this).")
  public void setDomain(final String domain) {
    this.domain = domain;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_INSTANCE, optional = true,
      priority = JtdsDataSourceAttributePriority.P19_INSTANCE, label = "Instance",
      description = "Named instance to connect to. SQL Server can run multiple so-called "
          + "\"named instances\" (i.e. different server instances, running on different TCP ports) "
          + "on the same machine. When using Microsoft tools, selecting one of these instances is "
          + "made by using \"<host_name>\\<instance_name>\" instead of the usual \"<host_name>\". "
          + "With jTDS you will have to split the two and use the instance name as a property.")
  public void setInstance(final String instance) {
    this.instance = instance;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_LANGUAGE, optional = true,
      priority = JtdsDataSourceAttributePriority.P20_LANGUAGE, label = "Language")
  public void setLanguage(final String language) {
    this.language = language;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_LAST_UPDATE_COUNT,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_LAST_UPDATE_COUNT,
      priority = JtdsDataSourceAttributePriority.P21_LAST_UPDATE_COUNT, label = "Last update count",
      description = "If true only the last update count will be returned by executeUpdate(). "
          + "This is useful in case you are updating or inserting into tables that have triggers "
          + "(such as replicated tables); there's no way to make the difference between an update "
          + "count returned by a trigger and the actual update count but the actual update count "
          + "is always the last as the triggers execute first. If false all update counts are "
          + "returned; use getMoreResults() to loop through them.")
  public void setLastUpdateCount(final boolean lastUpdateCount) {
    this.lastUpdateCount = lastUpdateCount;
  }

  @LongAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_LOB_BUFFER,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_LOB_BUFFER,
      priority = JtdsDataSourceAttributePriority.P22_LOB_BUFFER, label = "Lob buffer",
      description = "The amount of LOB data to buffer in memory before caching to disk. "
          + "The value is in bytes for Blob data and chars for Clob data.")
  public void setLobBuffer(final long lobBuffer) {
    this.lobBuffer = lobBuffer;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_LOG_FILE, optional = true,
      priority = JtdsDataSourceAttributePriority.P23_LOG_FILE, label = "Log file")
  public void setLogFile(final String logFile) {
    this.logFile = logFile;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_LOGIN_TIMEOUT,
      optional = true, priority = JtdsDataSourceAttributePriority.P24_LOGIN_TIMEOUT,
      label = "Login timeout",
      description = "The amount of time to wait (in seconds) for a successful connection before "
          + "timing out. If a TCP/IP connection is used to connect to the database and "
          + "Java 1.4 or newer is being used, the loginTimeout parameter is used to set the "
          + "initial connection timeout when initially opening a new socket. A value of zero "
          + "(the default) causes the connection to wait indefinitely, e.g.,until a connection is "
          + "established or an error occurs. See also socketTimeout. If a named pipe connection is "
          + "used (namedPipe is true) and loginTimeout is greater than zero, the value of "
          + "loginTimeout is used for the length of the retry period when "
          + "\"All pipe instances are busy\" error messages are received while attempting to "
          + "connect to the server. If loginTimeout is zero (the default), a value of 20 seconds "
          + "is used for the named pipe retry period. (default - 0 for TCP/IP connections or 20 "
          + "for named pipe connections)")
  public void setLoginTimeout(final Integer loginTimeout) {
    this.loginTimeout = loginTimeout;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_MAC_ADDRESS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_MAC_ADDRESS,
      priority = JtdsDataSourceAttributePriority.P25_MAC_ADDRESS, label = "MAC address",
      description = "Network interface card MAC address. It's displayed by Enterprise Manager or "
          + "Profiler associated with the connection and is needed to resolve some issues "
          + "regarding the number of clients allowed by the SQL Server license. The MAC address "
          + "cannot be determined automatically from Java (i.e. without using native code) so "
          + "you'll have to specify it yourself if you need it.")
  public void setMacAddress(final String macAddress) {
    this.macAddress = macAddress;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_MAX_STATEMENTS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_MAX_STATEMENTS,
      priority = JtdsDataSourceAttributePriority.P26_MAX_STATEMENTS, label = "Max statements",
      description = "The number of statement prepares each connection should cache. A value of 0 "
          + "will disable statement caching. A value of Integer.MAX_VALUE (2147483647) will enable "
          + "fast caching (uses less memory and has no overhead associated with removing "
          + "statements); the cache will never release any cached statements, so although "
          + "experience has shown that this is usually not a problem with most applications, "
          + "use with care.")
  public void setMaxStatements(final int maxStatements) {
    this.maxStatements = maxStatements;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_NAMED_PIPE,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_NAMED_PIPE,
      priority = JtdsDataSourceAttributePriority.P27_NAMED_PIPE, label = "Named pipe",
      description = "When set to true, named pipe communication is used to connect to the database "
          + "instead of TCP/IP sockets. When the os.name system property starts with \"windows\" "
          + "(case-insensitive), named pipes (both local and remote) are accessed through the "
          + "Windows filesystem by opening a RandomAccessFile to the path. When the SQL Server "
          + "and the client are on the same machine, a named pipe will usually have better "
          + "performance than TCP/IP sockets since the network layer is eliminated. Otherwise "
          + "the JCIFS library is used. JCIFS provides a pure Java named pipe implementation and "
          + "uses NTLM authentication, so the domain parameter is required. This feature supports "
          + "the instance parameter (which changes the named pipe URL), but it does not currently "
          + "support the named pipe at a location other than /sql/query on the server. "
          + "The port parameter is ignored if set.")
  public void setNamedPipe(final boolean namedPipe) {
    this.namedPipe = namedPipe;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_PACKET_SIZE,
      optional = true, priority = JtdsDataSourceAttributePriority.P28_PACKET_SIZE,
      label = "Packet size",
      description = "The network packet size (a multiple of 512). "
          + "(default - 4096 for TDS 7.0/8.0; 512 for TDS 4.2/5.0)")
  public void setPacketSize(final Integer packetSize) {
    this.packetSize = packetSize;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_PASSWORD,
      priority = JtdsDataSourceAttributePriority.P05_PASSWORD, label = "Password",
      description = "Password to use for login. When using "
          + "getConnection(String url, String user, String password) it's not required to set this "
          + "property as it is passed as parameter, but you will have to set it when using "
          + "getConnection(String url, Properties info) or JtdsDataSource.")
  public void setPassword(final String password) {
    this.password = password;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_PORT_NUMBER,
      optional = true, priority = JtdsDataSourceAttributePriority.P03_PORT_NUMBER, label = "Port",
      description = "The port the database server is listening to "
          + "(default - 1433 for SQL Server and 7100 for Sybase)")
  public void setPortNumber(final Integer portNumber) {
    this.portNumber = portNumber;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_PREPARE_SQL,
      optional = true, priority = JtdsDataSourceAttributePriority.P29_PREPARE_SQL,
      label = "Prepare SQL",
      description = "This parameter specifies the mechanism used for Prepared Statements. 0 - SQL "
          + "is sent to the server each time without any preparation, literals are inserted in "
          + "the SQL (slower). 1 - Temporary stored procedures are created for each unique SQL "
          + "statement and parameter combination (faster). 2 - sp_executesql is used (fast). "
          + "3 - sp_prepare and sp_cursorprepare are used in conjunction with sp_execute and"
          + " sp_cursorexecute (faster, SQL Server only). "
          + "(default - 3 for SQL Server, 1 for Sybase)")
  public void setPrepareSql(final Integer prepareSql) {
    this.prepareSql = prepareSql;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_PROCESS_ID,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_PROCESS_ID, label = "Process id",
      description = "The client process ID associated with the connection. "
          + "Must be an integer value or the string \"compute\" to let jTDS choose a process ID.")
  public void setProcessId(final String processId) {
    this.processId = processId;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_PROGRAM_NAME,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_PROGRAM_NAME,
      priority = JtdsDataSourceAttributePriority.P30_PROGRAM_NAME, label = "Program name",
      description = "Client library name. No practical use, "
          + "it's displayed by Enterprise Manager or Profiler associated with the connection.")
  public void setProgName(final String progName) {
    this.progName = progName;
  }

  @BooleanAttribute(
      attributeId = JtdsDataSourceComponentConstants.ATTR_SEND_STRING_PARAMETERS_AS_UNICODE,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_SEND_STRING_PARAMETERS_AS_UNICODE,
      priority = JtdsDataSourceAttributePriority.P31_SEND_STRING_PARAMETERS_AS_UNICODE,
      label = "Send string parameter as unicode",
      description = "Determines whether string parameters are sent to the SQL Server database in "
          + "Unicode or in the default character encoding of the database. This seriously affects "
          + "SQL Server 2000 performance since it does not automatically cast the types "
          + "(as 7.0 does), meaning that if a index column is Unicode and the string is submitted "
          + "using the default character encoding (or the other way around) SQLServer will perform "
          + "an index scan instead of an index seek. For Sybase, determines if strings that cannot "
          + "be encoded in the server's charset are sent as unicode strings. "
          + "There is a performance hit for the encoding logic so set this option to false if "
          + "unitext or univarchar data types are not in use or if charset is utf-8.")
  public void setSendStringParametersAsUnicode(final boolean sendStringParametersAsUnicode) {
    this.sendStringParametersAsUnicode = sendStringParametersAsUnicode;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_SERVER_NAME,
      priority = JtdsDataSourceAttributePriority.P02_SERVER_NAME, label = "Server name",
      description = "Name or IP of the database server")
  public void setServerName(final String serverName) {
    this.serverName = serverName;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_SERVER_TYPE,
      priority = JtdsDataSourceAttributePriority.P01_SERVER_TYPE, label = "Server type",
      description = "Type of the database server. 1 - sqlserver, 2 - sybase",
      options = { @IntegerAttributeOption(
          value = JtdsDataSourceComponentConstants.OPTION_VALUE_SERVERTYPE_SQLSERVER,
          label = "SQLServer"),
          @IntegerAttributeOption(
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SERVERTYPE_SYBASE,
              label = "Sybase") },
      defaultValue = JtdsDataSourceComponentConstants.OPTION_VALUE_SERVERTYPE_SQLSERVER)
  public void setServerType(final int serverType) {
    this.serverType = serverType;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_SOCKET_KEEP_ALIVE,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_SOCKET_KEEP_ALIVE,
      priority = JtdsDataSourceAttributePriority.P32_SOCKET_KEEP_ALIVE, label = "Socket keepalive",
      description = "True to enable TCP/IP keep-alive messages.")
  public void setSocketKeepAlive(final boolean socketKeepAlive) {
    this.socketKeepAlive = socketKeepAlive;
  }

  @IntegerAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_SOCKET_TIMEOUT,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_SOCKET_TIMEOUT,
      priority = JtdsDataSourceAttributePriority.P33_SOCKET_TIMEOUT, label = "Socket timeout",
      description = "The amount of time to wait (in seconds) for a server response before timing "
          + "out. Use with care! If a non zero value is supplied this must be greater than the "
          + "maximum time that the server will take to answer any query. Once the timeout value is "
          + "exceeded the network or named pipe connection will be closed. This parameter may be "
          + "useful for detecting dead network connections in a pooled environment. See also "
          + "loginTimeout. If using named pipes via JCIFS the timeout cannot be disabled "
          + "completely. A timeout of about 25 days (2^31 ms) is applied instead.")
  public void setSocketTimeout(final int socketTimeout) {
    this.socketTimeout = socketTimeout;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_SSL,
      priority = JtdsDataSourceAttributePriority.P08_SSL, label = "SSL",
      description = "Specifies if and how to use SSL for secure communication. "
          + "off - SSL is not request or used; this is the default. "
          + "request - SSL is requested; if the server does not support it then a plain connection "
          + "is used. require - SSL is requested; if the server does not support it then an "
          + "exception is thrown. authenticate - Same as require except the server's "
          + "certificate must be signed by a trusted CA.",
      options = {
          @StringAttributeOption(label = "Off",
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_OFF),
          @StringAttributeOption(label = "Request",
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_REQUEST),
          @StringAttributeOption(label = "Require",
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_REQUIRE),
          @StringAttributeOption(label = "Authenticate",
              value = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_AUTHENTICATE) },
      defaultValue = JtdsDataSourceComponentConstants.OPTION_VALUE_SSL_OFF)
  public void setSsl(final String ssl) {
    this.ssl = ssl;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_TCP_NO_DELAY,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_TCP_NO_DELAY,
      priority = JtdsDataSourceAttributePriority.P34_TCP_NO_DELAY, label = "TCP no delay",
      description = "true to enable TCP_NODELAY on the socket; false to disable it.")
  public void setTcpNoDelay(final boolean tcpNoDelay) {
    this.tcpNoDelay = tcpNoDelay;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_TDS, optional = true,
      priority = JtdsDataSourceAttributePriority.P35_TDS, label = "TDS",
      description = "The version of TDS to be used. TDS (Tabular Data Stream) is the protocol "
          + "used by Microsoft SQL Server and Sybase to communicate with database clients. "
          + "jTDS can use TDS 4.2, 5.0, 7.0 and 8.0. Version 4.2 is used by SQL Server 6.5 and"
          + " Sybase 10. Version 5.0 is used with Sybase 11 onwards. Version 7.0 is used by "
          + "SQL Server 7.0; this protocol also works with SQL Server 2000. Version 8.0 is used "
          + "by SQL Server 2000 and SQL Server 2005. Newer database server versions usually "
          + "understand older protocol versions. This means that SQL Server 7.0 can be used with "
          + "TDS 4.2, but the limitations of the protocol apply regardless of the server version "
          + "(e.g. when using TDS 4.2 VARCHARs are limited to 255 characters). As a conclusion, "
          + "you must set this property to \"4.2\" when connecting to SQL Server 6.5 or Sybase. "
          + "You should not set this value to \"7.0\" or \"8.0\") when connecting to any "
          + "version of Sybase as these are SQL Server specific protocols. Further, you should not "
          + "set this value to \"5.0\") when connecting to any version of SQL Server as this is a "
          + "Sybase specific protocol. Currently jTDS automatically falls back from 8.0 to 7.0 "
          + "(if used with SQL Server 7.0) and from 5.0 to 4.2 (with Sybase 10) so specifying the "
          + "value for this parameter is only necessary for SQL Server 6.5. "
          + "(default - \"8.0\" for SQL Server; \"5.0\" for Sybase)")
  public void setTds(final String tds) {
    this.tds = tds;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_USE_CURSORS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USE_CURSORS,
      priority = JtdsDataSourceAttributePriority.P36_USE_CURSORS, label = "User cursors",
      description = "Instructs jTDS to use server side cursors instead of direct selects "
          + "(AKA firehose cursors) for forward-only read-only result sets "
          + "(with other types of result sets server- or client-side cursors are always used). "
          + "With firehose cursors the SELECT query is sent and the server responds with all the "
          + "resulting rows. This is the fastest approach but it means that the driver has to "
          + "cache all results if another request needs to be made before all rows have been "
          + "processed. So when using multiple Statements per Connection it is preferable to have "
          + "server-side cursors instead; these will allow the driver to request only a limited "
          + "number of rows at a time (controllable through the fetchSize property of a Statement)."
          + " This means extra request-response cycles, but less caching by the driver. With "
          + "SQL Server a so called fast forward-only cursor will be created when this property is "
          + "set to true. With Sybase a usual forward-only read-only cursor is created.")
  public void setUseCursors(final boolean useCursors) {
    this.useCursors = useCursors;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_USE_JCIFS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USE_JCIFS,
      priority = JtdsDataSourceAttributePriority.P37_USE_JCIFS, label = "Use JCIFS",
      description = "Controls whether the jCIFS library will be used instead of the local file "
          + "system with named pipe connections on the Windows operating system. "
          + "(The jCIFS library will always be used with named pipes when the operating system is "
          + "not Windows.) Useful when connecting via named pipes to a server that is located in a "
          + "different domain than the client. See also namedPipe.")
  public void setUseJCIFS(final boolean useJCIFS) {
    this.useJCIFS = useJCIFS;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_USE_KERBEROS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USE_KERBEROS,
      priority = JtdsDataSourceAttributePriority.P38_USE_KERBEROS, label = "Use kerberos")
  public void setUseKerberos(final boolean useKerberos) {
    this.useKerberos = useKerberos;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_USE_LOBS,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USE_LOBS,
      priority = JtdsDataSourceAttributePriority.P39_USE_LOBS, label = "Use LOBs",
      description = "Controls whether large types (IMAGE and TEXT/NTEXT) should be mapped by "
          + "default (when using getObject()) to LOBs or Java types (String and byte[]). "
          + "The default JDBC type constant returned is also controlled by this property: "
          + "Types.BLOB for IMAGE and Types.CLOB for TEXT/NTEXT when true, "
          + "Types.LONGVARBINARY for IMAGE and Types.LONGVARCHAR for TEXT/NTEXT when false. "
          + "This is useful when printing out directly the values returned by getObject() "
          + "(e.g. when using JSTL or other frameworks), as Blob and Clob don't implement "
          + "toString() (both because it's not required and because it can easily lead to "
          + "OutOfMemoryErrors in unexpected situations, such as when logging data). "
          + "The default setting of true has the advantage that the amount of data that is cached "
          + "in memory for a large object can be controlled via the lobBuffer property; "
          + "a setting of false will still use the Blob and Clob implementations internally but "
          + "the values will be materialized to memory when getObject() is called, possibly "
          + "leading to memory issues.")
  public void setUseLOBs(final boolean useLOBs) {
    this.useLOBs = useLOBs;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_USE_NTLMV2,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_USE_NTLMV2,
      priority = JtdsDataSourceAttributePriority.P40_USE_NTLMV2, label = "Use NTLMv2",
      description = "Set to true to send LMv2/NTLMv2 responses when using Windows authentication.")
  public void setUseNTLMv2(final boolean useNTLMv2) {
    this.useNTLMv2 = useNTLMv2;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_USER,
      priority = JtdsDataSourceAttributePriority.P04_USER, label = "User",
      description = "User name to use for login. When using "
          + "getConnection(String url, String user, String password) it's not required to set "
          + "this property as it is passed as parameter, but you will have to set it when using "
          + "getConnection(String url, Properties info) or JtdsDataSource.")
  public void setUser(final String user) {
    this.user = user;
  }

  @StringAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_WSID, optional = true,
      priority = JtdsDataSourceAttributePriority.P41_WSID, label = "WSID",
      description = "Workstation ID. No practical use, it's displayed by Enterprise Manager or "
          + "Profiler associated with the connection. (default - the client host name)")
  public void setWsid(final String wsid) {
    this.wsid = wsid;
  }

  @BooleanAttribute(attributeId = JtdsDataSourceComponentConstants.ATTR_XA_EMULATION,
      defaultValue = JtdsDataSourceComponentConstants.DEFAULT_XA_EMULATION, label = "XA emulation",
      description = "When set to true, emulate XA distributed transaction support, when set to "
          + "false use experimental true distributed transaction support. True distributed "
          + "transaction support is only available for SQL Server 2000 and requires the "
          + "installation of an external stored procedure in the target server "
          + "(see the README.XA file in the distribution for details).")
  public void setXaEmulation(final boolean xaEmulation) {
    this.xaEmulation = xaEmulation;
  }

  private void updatePropertiesAToD(final JtdsDataSource jtdsDataSource) {
    jtdsDataSource.setAppName(appName);
    jtdsDataSource.setAutoCommit(autoCommit);
    if (batchSize != null) {
      jtdsDataSource.setBatchSize(batchSize);
    }
    if (bindAddress != null) {
      jtdsDataSource.setBindAddress(bindAddress);
    }
    if (bufferDir != null) {
      jtdsDataSource.setBufferDir(bufferDir);
    }
    jtdsDataSource.setBufferMaxMemory(bufferMaxMemory);
    jtdsDataSource.setBufferMinPackets(bufferMinPackets);
    jtdsDataSource.setCacheMetaData(cacheMetaData);
    if (charset != null) {
      jtdsDataSource.setCharset(charset);
    }
    if (databaseName != null) {
      jtdsDataSource.setDatabaseName(databaseName);
    }
    if (description != null) {
      jtdsDataSource.setDescription(description);
    }
    if (domain != null) {
      jtdsDataSource.setDomain(domain);
    }
  }

  private void updatePropertiesIToN(final JtdsDataSource jtdsDataSource) {
    if (instance != null) {
      jtdsDataSource.setInstance(instance);
    }
    if (language != null) {
      jtdsDataSource.setLanguage(language);
    }
    jtdsDataSource.setLastUpdateCount(lastUpdateCount);
    jtdsDataSource.setLobBuffer(lobBuffer);
    if (logFile != null) {
      jtdsDataSource.setLogFile(logFile);
    }
    if (loginTimeout != null) {
      jtdsDataSource.setLoginTimeout(loginTimeout);
    }
    jtdsDataSource.setMacAddress(macAddress);
    jtdsDataSource.setMaxStatements(maxStatements);
    jtdsDataSource.setNamedPipe(namedPipe);
  }

  private void updatePropertiesPToX(final JtdsDataSource jtdsDataSource) {
    if (packetSize != null) {
      jtdsDataSource.setPacketSize(packetSize);
    }
    jtdsDataSource.setPassword(password);
    if (portNumber != null) {
      jtdsDataSource.setPortNumber(portNumber);
    }
    if (prepareSql != null) {
      jtdsDataSource.setPrepareSql(prepareSql);
    }
    jtdsDataSource.setProcessId(processId);
    jtdsDataSource.setProgName(progName);
    jtdsDataSource.setSendStringParametersAsUnicode(sendStringParametersAsUnicode);
    jtdsDataSource.setServerName(serverName);
    jtdsDataSource.setServerType(serverType);
    jtdsDataSource.setSocketKeepAlive(socketKeepAlive);
    jtdsDataSource.setSocketTimeout(socketTimeout);
    jtdsDataSource.setSsl(ssl);
    jtdsDataSource.setTcpNoDelay(tcpNoDelay);
    if (tds != null) {
      jtdsDataSource.setTds(tds);
    }
    jtdsDataSource.setUseCursors(useCursors);
    jtdsDataSource.setUseJCIFS(useJCIFS);
    jtdsDataSource.setUseKerberos(useKerberos);
    jtdsDataSource.setUseLOBs(useLOBs);
    jtdsDataSource.setUseNTLMV2(useNTLMv2);
    jtdsDataSource.setUser(user);
    if (wsid != null) {
      jtdsDataSource.setWsid(wsid);
    }
    jtdsDataSource.setXaEmulation(xaEmulation);
  }
}
