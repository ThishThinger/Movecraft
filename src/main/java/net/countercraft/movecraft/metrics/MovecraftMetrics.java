/*
 * This file is part of Movecraft.
 *
 *     Movecraft is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Movecraft is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Movecraft.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.countercraft.movecraft.metrics;

import net.countercraft.movecraft.Movecraft;
import net.countercraft.movecraft.localisation.L18nSupport;
import org.mcstats.Metrics;

import java.io.IOException;
import java.util.logging.Level;

public class MovecraftMetrics {
	private int classTypes;

	public MovecraftMetrics(int classTypes) {
		this.classTypes = classTypes;
		uploadStatistics();
	}

	private void uploadStatistics() {
		try {
			Metrics metrics = new Metrics(Movecraft.getInstance());

			if ( metrics.isOptOut() ) {
				Movecraft.getInstance().getLogger().log( Level.INFO, String.format( L18nSupport.getInternationalisedString( "MCStats - :( - Admin has opted out" ) ) );
			}

			metrics.addCustomData( new Metrics.Plotter("Number of Craft Types") {

				@Override
				public int getValue() {
					return classTypes;
				}

			} );
			metrics.start();
			Movecraft.getInstance().getLogger().log( Level.INFO, String.format( L18nSupport.getInternationalisedString( "MCStats - Thank you message" ) ) );
		} catch (IOException e) {
			Movecraft.getInstance().getLogger().log( Level.WARNING, String.format( L18nSupport.getInternationalisedString( "MCStats - Error - Unable to upload stats" ) ) );
		}
	}

}
