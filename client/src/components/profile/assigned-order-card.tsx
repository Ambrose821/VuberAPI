import { Order, Parcel } from '@/types/types'
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import { MapPin } from 'lucide-react'

export default function AssignedOrderCard({ order }: { order: Order }) {
  const calculateTotalWeight = (parcels: Parcel[]) => {
    const kgParcels = parcels
      .filter((p) => p.weightUnit === 'kg')
      .reduce((acc, p) => acc + p.weight, 0)

    const lbParcels = parcels
      .filter((p) => p.weightUnit === 'lb')
      .reduce((acc, p) => acc + p.weight * 0.453592, 0) // Convert lb to kg

    return {
      totalKg: kgParcels + lbParcels,
      parcelCount: parcels.length,
    }
  }

  const { totalKg, parcelCount } = calculateTotalWeight(order.parcels)

  return (
    <Card className="hover:shadow-lg transition-shadow duration-200">
      <CardHeader>
        <CardTitle className="flex flex-wrap gap-2 justify-between items-center">
          <span>Order #{order.id}</span>
          <span className="text-sm font-normal bg-blue-100 text-blue-800 px-3 py-1 rounded-full">
            {parcelCount} {parcelCount === 1 ? 'parcel' : 'parcels'} •{' '}
            {totalKg.toFixed(1)} kg total
          </span>
        </CardTitle>
      </CardHeader>
      <CardContent>
        <div className="space-y-4">
          <div className="w-fit">
            <div className="flex items-start gap-2">
              <MapPin className="mt-1 h-5 w-5 text-green-600 flex-shrink-0" />
              <p className="font-medium text-green-700">Pickup Location</p>
            </div>
            <p className="text-sm text-gray-600">{order.origin}</p>
            <div className="mt-1 text-sm bg-green-50 text-green-700 p-2 rounded">
              <strong>Instructions:</strong> {order.pickupInstructions}
            </div>
          </div>

          <div className="w-fit">
            <div className="flex items-start gap-2">
              <MapPin className="mt-1 h-5 w-5 text-red-600 flex-shrink-0" />
              <p className="font-medium text-red-700">Dropoff Location</p>
            </div>
            <p className="text-sm text-gray-600">{order.destination}</p>
            <div className="mt-1 text-sm bg-red-50 text-red-700 p-2 rounded">
              <strong>Instructions:</strong> {order.dropoffInstructions}
            </div>
          </div>

          <button
            onClick={() => console.log('Mark as Delivered')}
            className="w-full mt-4 bg-blue-50 text-blue-600 hover:bg-blue-100 py-2 rounded-md transition-colors duration-200"
          >
            Mark as Delivered
          </button>
        </div>
      </CardContent>
    </Card>
  )
}
