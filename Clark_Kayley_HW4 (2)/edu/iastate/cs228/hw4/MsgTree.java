package edu.iastate.cs228.hw4;

public class MsgTree {
	// @author Kayley Clark

	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	// Need static char idx to the tree string for recursive solution
	private static int staticCharIdx = 0;

	/*
	 * @Kayley Clark The amount of times I rewrote this only to find it work with
	 * the most simple solution is bafflling, I mean truly spent hours debugging
	 * this. It worked for the first two tests then wouldn't work, but alas. I
	 * started with a lot of if statements to check null objects, if it's just in
	 * the right if it's just in the left, but finally deleted half of those and
	 * made it very simple.
	 */
	public MsgTree(String encodingString) {
		if (encodingString.charAt(staticCharIdx) != '^') {
			payloadChar = encodingString.charAt(staticCharIdx);
			staticCharIdx++;
		} else if (encodingString.charAt(staticCharIdx) == '^') {
			staticCharIdx++;
			left = new MsgTree(encodingString);
			right = new MsgTree(encodingString);
		}
	}

	public MsgTree(char payloadChar) {
		this.payloadChar = payloadChar;
		this.left = null;
		this.right = null;
	}

	/*
	 * @author Kayley Clark printCodes, the challenge here was to do this
	 * reccursively, and use preorder traversal. My other challenge was figuring out
	 * that theCode and return part, this would not work for awhile and then I
	 * rewrote it and now works fine.
	 */
	public static void printCodes(MsgTree root, String path) {
		String theCode = path;
		if (root.left == null && root.right == null) {
			System.out.println("    " + (root.payloadChar == '\n' ? "\\n" : root.payloadChar + " ") + "    " + path);
			// clearing the path so it can start at the same character in the string.
			theCode = "";
			return;
		}
		if (root.left != null) {
			printCodes(root.left, theCode + "0");
		}
		if (root.right != null) {
			printCodes(root.right, theCode + "1");
		}
	}

	/*
	 * @author Kayley Clark Original though process, included a single while loop
	 * and to call decode recursively by decreasing the size until the message
	 * length was one and then it would print the final character. However I
	 * continuously ran into a stack overflow error, and am still unsure why as the
	 * two small tests work however the last two did not. Reworked the method into
	 * this and with a lot of debugging this works.
	 */
	public void decode(MsgTree codes, String msg) {
		// create a new tree
		MsgTree inTree = codes;
		int i = 0;
		while (i < msg.length()) {
			// checks to make sure that we are not at a payload char
			while (inTree.left != null && inTree.right != null) {
				// 0 corresponds with left, 1 with right, traversing through the tree
				if (msg.charAt(i) == '0') {
					inTree = inTree.left;
				} else if (msg.charAt(i) == '1') {
					inTree = inTree.right;
				}
				i++;
			}
			// Output the letter then starting over again
			System.out.print(inTree.payloadChar);
			inTree = codes;
		}
	}
}
