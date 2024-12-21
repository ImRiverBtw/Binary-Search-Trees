package main.java;

import main.java.models.VoteEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class XMLParser {

    public List<VoteEntry> parseVoteEntries(String directoryPath) {
        List<VoteEntry> voteEntries = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory path: " + directoryPath);
        }

        System.out.println("parsing...");
        // Iterate over XML files in the directory
        for (File file : directory.listFiles((dir, name) -> name.endsWith(".xml"))) {
            try {
                System.out.println("parsing..." + file.getName());
                String xmlContent = Files.readString(Paths.get(file.getAbsolutePath()));
                VoteEntry voteEntry = parseVoteEntry(xmlContent);

                // Add to the list if a valid VoteEntry was found
                if (voteEntry != null) {
                    voteEntries.add(voteEntry);
                }
            } catch (Exception e) {
                System.err.println("Error processing file: " + file.getName() + " - " + e.getMessage());
            }
        }
        return voteEntries;
    }

    private VoteEntry parseVoteEntry(String xmlContent) {
        try {
            // Initialize Document Builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML content
            Document document = builder.parse(new ByteArrayInputStream(xmlContent.getBytes()));

            // Normalize XML structure
            document.getDocumentElement().normalize();

            // Extract ContestName
            NodeList contestNodes = document.getElementsByTagName("Contest");
            if (contestNodes.getLength() == 0) {
                return null; // Skip if no contest found
            }
            Element contestElement = (Element) contestNodes.item(0);
            String contestName = contestElement.getElementsByTagName("ContestName").item(0).getTextContent();

            // Find the Selection node with CandidateIdentifier Id="1"
            NodeList selectionNodes = document.getElementsByTagName("Selection");
            for (int i = 0; i < selectionNodes.getLength(); i++) {
                Element selectionElement = (Element) selectionNodes.item(i);

                // Check for CandidateIdentifier Id="1"
                NodeList candidateNodes = selectionElement.getElementsByTagName("CandidateIdentifier");
                if (candidateNodes.getLength() > 0) {
                    Element candidateElement = (Element) candidateNodes.item(0);
                    if ("1".equals(candidateElement.getAttribute("Id"))) {
                        // Extract ValidVotes
                        String validVotesText = selectionElement.getElementsByTagName("ValidVotes").item(0).getTextContent();
                        int validVotes = Integer.parseInt(validVotesText);
                        // Return a new VoteEntry
                        return new VoteEntry(contestName, validVotes);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing XML content: " + e.getMessage());
        }
        return null; // Return null if no matching candidate is found

}
}