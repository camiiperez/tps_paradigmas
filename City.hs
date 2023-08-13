module City (City(..), newC,nameC) where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC str point = Cit str point

nameC :: City -> String
nameC (Cit str point) = str

getCoords :: City -> Point
getCoords (Cit str point) = point

bsas = newC "Buenos Aires" (newP 5 4)
mtz = newC "Martinez" (newP 1 2)

distanceC :: City -> City -> Float
distanceC city1 city2 = difP(getCoords(city1)) (getCoords(city2))

