package workshop.mirceasoit.luas.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import workshop.mirceasoit.luas.model.Direction

@Root(strict = false, name="stopInfo")
data class StopInfo @JvmOverloads constructor (
    @field:Attribute(name = "stop") var stop: String = "",
    @field:ElementList(entry = "direction", required = false, inline = true) var directions: MutableList<Direction> = mutableListOf()
)