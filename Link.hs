module Link ( Link(Lin), newL,connectsL,linksL,capacityL,delayL,cityNamesInLinks,citiesTypeInLinks) where

import Point
import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)
--
newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality = Lin city1 city2 quality
-----------

cityNamesInLinks :: Link -> [String]
cityNamesInLinks (Lin city1 city2 quality) = [nameC(city1),nameC(city2)] 

citiesTypeInLinks :: Link -> [City]
citiesTypeInLinks (Lin city1 city2 quality) = [city1,city2] 

------------
connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL (Cit str point) (Lin (Cit str' point') (Cit str'' point'') quality) = elem str [str',str'']


linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL (Cit str point) (Cit str' point') (Lin city1 city2 quality) = (elem str [nameC(city1)] || elem str [nameC(city1)]) && (elem str' [nameC(city2)] || elem str' [nameC(city2)])
   
capacityL :: Link -> Int
capacityL (Lin city1 city2 quality) = capacityQ(quality)

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 quality) = delayQ(quality)
-------------

olivos = newC "Olivos" (newP 5 4)
mtz = newC "Martinez" (newP 1 2)
berazategui = newC "Berazategui" (newP 8 4)

fibraOptica = newQ "Fibra Optica" 4 22.3

sanIsidro = newL olivos mtz fibraOptica



