module Link ( Link(..), newL,connectsL,linksL,capacityL,delayL,cityNamesInLinks) where

import Point
import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality = Lin city1 city2 quality

cityNamesInLinks :: Link -> [String]
cityNamesInLinks (Lin city1 city2 quality) = [nameC(city1),nameC(city2)] 

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city link = elem (nameC(city)) (cityNamesInLinks(link))


linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link = (elem (nameC(city1)) (cityNamesInLinks(link))) && (elem (nameC(city2)) (cityNamesInLinks(link)))

capacityL :: Link -> Int
capacityL (Lin city1 city2 quality) = capacityQ(quality)


delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 quality) = delayQ(quality)

olivos = newC "Olivos" (newP 5 4)
mtz = newC "Martinez" (newP 1 2)
berazategui = newC "Berazategui" (newP 8 4)

fibraOptica = newQ "Fibra Optica" 4 22.3

sanIsidro = newL olivos mtz fibraOptica



