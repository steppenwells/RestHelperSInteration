package code
package lib

import model._

import net.liftweb._
import common._
import http._
import rest._
import json._
import scala.xml._
import net.liftweb.http.S

object SInteractingHelper extends RestHelper {

  implicit def itemToResponseByAccepts: JxCvtPF[Item] = {
    case (JsonSelect, c, _) => c: JValue
    case (XmlSelect, c, _) => c: Node
  }

  // serve an item and should set example header
  // header currently not being set
  serveJx[Item] {
    "servejx" / "header" prefixJx {
      case Item(item) :: Nil Get _ => {

        S.setHeader("example", "should be set")
        item
      }
    }
  }

  // serve an item and override it's description with description set by param
  // param can be read from S fine
  serveJx[Item] {
    "servejx" / "param" prefixJx {
      case Item(item) :: Nil Get _ => {

        val descOverride = S.param("desc")
        item.copy(description = descOverride.openOr(item.description))
      }
    }
  }

  // serve an item and set example header, this version manually returns a response as opposed to using serveJx
  // in this version the header set via S is used correctly
  serve {
    "serve" / "resp" prefix {
      case Item(item) :: Nil JsonGet _ => {
        S.setHeader("example", "should be set")
        JsonResponse(Item.find("1234").open_! :JValue)
      }
    }
  }
}