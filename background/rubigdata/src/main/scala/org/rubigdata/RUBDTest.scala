package org.rubigdata

import nl.surfsara.warcutils.WarcInputFormat
import org.jwat.warc.{WarcConstants, WarcRecord}

import org.apache.hadoop.io.LongWritable;

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object RUBDTest {

  def main(args: Array[String]) {
    val CCdir = "/data/public/common-crawl/crawl-data/CC-MAIN-2016-07/"
    val CCseg = "1454702039825.90"
    val fnm = "CC-MAIN-20160205195359-00348-ip-10-236-182-209.ec2.internal.warc.gz"

    val CCfile = CCdir + "segments/" + CCseg + "/warc/" + fnm

    val conf = new SparkConf().setAppName("RUBDTest")
    val sc = new SparkContext(conf)

    val warc = sc.newAPIHadoopFile(
                CCfile,
                classOf[WarcInputFormat],               // InputFormat
                classOf[LongWritable],                  // Key  
                classOf[WarcRecord]                     // Value
        )
    val nHTML = warc.count()

/*
    val nHTML = warc.
      filter(warcfile => warcFile.type("HTML")).
      count()
*/
    println("%s HTML pages in WARC file %s ".format(nHTML, fnm))
  }

}

