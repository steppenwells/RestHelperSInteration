This is a copy of the simplylift rest hepler example
with an additional set of endpoints that interact with
the S object. These are defined in the src/main/scala/cod/lib/SInteractingHelper
object. There are 3 examples, one of which does
not work as expected:

/servejx/header/<item> 
serves (using serveJx) an item and sets a response
header. Not currently working.

/servejx/param/<item>?desc=
serves (using serveJx) an item and override description
with request parameter. Works fine

/serve/resp/<item>
serves an item using serve method that sets a header
using S and creates the response manually. Header
is set correctly.
