module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP x y = Poi x y

diferenciaEnX :: Point -> Point -> Float
diferenciaEnX (Poi x y) (Poi x' y') = abs(fromIntegral (x - x'))

diferenciaEnY :: Point -> Point -> Float
diferenciaEnY (Poi x y) (Poi x' y') = abs(fromIntegral (y - y'))

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x y) (Poi x' y') = sqrt((diferenciaEnX (Poi x y) (Poi x' y'))**2 + (diferenciaEnY (Poi x y) (Poi x' y'))** 2)
