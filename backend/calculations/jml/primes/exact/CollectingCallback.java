/*
 * java-math-library is a Java library focused on number theory, but not necessarily limited to it. It is based on the PSIQS 4.0 factoring project.
 * Copyright (C) 2019 Tilman Neumann - tilman.neumann@web.de
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */
package de.tilman_neumann.jml.primes.exact;

public class CollectingCallback implements SieveCallback {
	private int capacity;
	public int count;
	public int[] array;
	
	public CollectingCallback(int desiredCount) {
		array = new int[desiredCount];
		capacity = desiredCount;
		count = 0;
	}
	
	public void processPrime(long prime) {
		if (count == capacity) return;
		//LOG.debug("Set p(" + count + ") = " + prime);
		array[count++] = (int) prime;
	}
}