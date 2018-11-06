package io.coderunner.shrinkwrap

import java.io.File

case class ShrinkWrapFile(file: File, config: Config) {
  val path            = file.getAbsolutePath
  val transcodeSuffix = config.transcodeSuffix
  val outputExtension = config.outputExtension

  /**
    * Whether the file is an original or transcoded file (determined by checking for the transcode suffix)
    * @return trie of the file is an original, non transcode
    */
  def isOriginal: Boolean = !file.getName.stripSuffix("." + extension).endsWith(config.transcodeSuffix)

  /**
    * Whether this particular file has an transcoded version of it available in the same dir
    * @return true if the file has a transcode
    */
  def hasBeenTranscoded: Boolean = transcodedFile.exists()

  def extension: String = {
    val lastDot = path.lastIndexOf(".")
    if (lastDot != -1) path.substring(lastDot + 1) else ""
  }

  def transcodedFile: File =
    new File(s"${path.take(path.lastIndexOf("."))}${transcodeSuffix}.${outputExtension}")

}
