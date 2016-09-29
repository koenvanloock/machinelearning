package helpers

import java.io.{InputStreamReader, File}

import week1models.House

import scala.io.Source

object CsvParser {

  def parse[T](fileName: String, lineParserFunction: (String) => T ): List[T] = {
    if (fileName.endsWith(".csv")) {
      val file = new File(getClass.getClassLoader.getResource(fileName).getFile)
      val source = Source.fromFile(file)
      // skip the headers
      val lines = source.getLines().toStream.tail.map(lineParserFunction)
      lines.toList
    } else{
      println("Er trad een fout op tijdens het lezen van de file!")
      List()
    }
  }
}
