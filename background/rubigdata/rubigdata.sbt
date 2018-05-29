name		:= "RUBigDataApp"
version		:= "1.0"
scalaVersion	:= "2.11.8"

packAutoSettings

val sparkV	= "2.1.2"
val hadoopV	= "2.7.2"
val jwatV	= "1.0.0"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkV % "provided",
  "org.apache.hadoop" %  "hadoop-client" % hadoopV % "provided",
  "org.jwat"          % "jwat-common"    % jwatV,
  "org.jwat"          % "jwat-warc"      % jwatV,
  "org.jwat"          % "jwat-gzip"      % jwatV
)

libraryDependencies += "com.github.sara-nl" % "warcutils" % "-SNAPSHOT"	
